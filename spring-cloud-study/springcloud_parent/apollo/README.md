# Apollo 配置中心集成文档

## 一、Apollo 简介

Apollo（阿波罗）是携程框架部门研发的分布式配置中心，能够集中化管理应用不同环境、不同集群的配置，配置修改后能够实时推送到应用端，并具备规范的权限、流程治理等特性。

### 核心功能
- **统一配置管理**：多环境（DEV/FAT/UAT/PRO）配置集中管理
- **实时推送**：配置修改后秒级生效
- **权限管理**：配置发布审核机制
- **灰度发布**：支持配置灰度发布

## 二、架构说明

```
┌─────────────┐     ┌─────────────┐     ┌─────────────────┐
│  Portal(8070)│────▶│ConfigService│────▶│ 本机 MySQL DB   │
│   管理界面    │     │  (8080)     │     │localhost:3306   │
└─────────────┘     │             │     │ ApolloConfigDB  │
                    │  AdminService│     │ ApolloPortalDB  │
                    │  (8090)     │     └─────────────────┘
                    └─────────────┘       (host.docker.internal)
```

| 服务 | 端口 | 说明 |
|------|------|------|
| Portal | 8070 | Web 管理界面 |
| ConfigService | 8080 | 配置读取/推送服务 |
| AdminService | 8090 | 配置管理服务 |
| MySQL | 3306 | 数据存储 |

## 三、快速启动

### 3.1 启动 Apollo 服务端

```bash
# 前置条件：本机 MySQL 需要运行，且已导入 Apollo 数据库
# 首次使用需要先初始化数据库：
# mysql -uroot -p12345678 < sql/apolloconfigdb.sql  (指定 ApolloConfigDB)
# mysql -uroot -p12345678 < sql/apolloportaldb.sql  (指定 ApolloPortalDB)

# 进入 apollo 目录
cd apollo

# 一键启动所有服务（Portal + ConfigService + AdminService）
docker-compose up -d

# 查看服务状态
docker-compose ps

# 查看日志
docker-compose logs -f
```

### 3.2 访问管理界面

- 地址：http://localhost:8070
- 默认账号：`apollo`
- 默认密码：`admin`

### 3.3 首次使用配置

1. 登录 Portal 后，点击「创建项目」
2. 填写项目信息：
   - 部门：可自定义
   - 应用 AppId：需与各服务 bootstrap.yml 中的 `app.id` 一致
   - 应用名称：便于识别
3. 在项目配置页面添加配置项
4. 发布配置

## 四、各服务 AppId 映射

| 模块 | AppId | 说明 |
|------|-------|------|
| springcloud_01eureka_server | eureka-server | 注册中心 |
| springcloud_02eureka_client | eureka-client | Eureka 客户端 |
| springcloud_03eureka_client02 | eureka-client02 | Eureka 客户端02 |
| springcloud_04eureka_client01 | eureka-client01 | Eureka 客户端01 |
| springcloud_05Hystrix_01 | hystrix-client | Hystrix 熔断 |
| springcloud_06feign_client01 | feign-client | Feign 调用 |
| springcloud_07hystrix_dashboard | hystrix-dashboard | Hystrix 仪表盘 |
| springcloud_08gateway | gateway-service | 网关服务 |
| springcloud_09_sharding_order | sharding-order | 分库分表订单服务 |

## 五、客户端集成方式

### 5.1 添加依赖（pom.xml）

```xml
<dependency>
    <groupId>com.ctrip.framework.apollo</groupId>
    <artifactId>apollo-client</artifactId>
    <version>${apollo.version}</version>
</dependency>
```

### 5.2 启动类添加注解

```java
@EnableApolloConfig
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

### 5.3 配置 bootstrap.yml

```yaml
app:
  id: your-app-id    # 必须与 Apollo Portal 中创建的项目 AppId 一致

apollo:
  bootstrap:
    enabled: true          # 开启 Apollo 启动阶段注入
    namespaces: application # 默认命名空间
  meta: http://localhost:8080  # ConfigService 地址
  cache-dir: /opt/data/apollo-cache-your-app  # 本地缓存目录
```

### 5.4 使用配置

```java
// 方式一：@Value 注解
@Value("${some.key:defaultValue}")
private String someValue;

// 方式二：@ConfigurationProperties（需配合 @EnableApolloConfig）
@ConfigurationProperties(prefix = "my.config")
public class MyConfig {
    private String key;
    // getter/setter
}
```

## 六、环境切换

通过 JVM 参数 `-Denv` 指定环境：

```bash
# 开发环境
java -Denv=DEV -jar your-app.jar

# 测试环境
java -Denv=FAT -jar your-app.jar

# 预发布环境
java -Denv=UAT -jar your-app.jar

# 生产环境
java -Denv=PRO -jar your-app.jar
```

## 七、常用运维命令

```bash
# 停止 Apollo
docker-compose down

# 停止并清除数据
docker-compose down -v

# 重新构建并启动
docker-compose up -d --build

# 查看 Portal 日志
docker-compose logs -f apollo-portal
```

## 八、注意事项

1. **启动顺序**：Apollo 服务端启动需要约 30-60 秒，请确保服务完全启动后再启动微服务
2. **本地缓存**：Apollo 客户端会在本地缓存配置，即使 Apollo 服务不可用，服务也能用缓存启动
3. **配置优先级**：Apollo 配置 > application.yml > bootstrap.yml
4. **IDE 中运行**：如不连接 Apollo，服务仍可用本地配置正常启动（降级机制）