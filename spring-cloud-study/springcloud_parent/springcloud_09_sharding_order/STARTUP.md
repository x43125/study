# 订单服务 - 启动指南

## ⚠️ 重要提示：Java环境配置

在macOS上，如果遇到 `No compiler is provided in this environment` 错误，需要设置正确的JAVA_HOME：

### 方法1：临时设置（当前终端有效）
```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_351.jdk/Contents/Home
```

### 方法2：永久设置（添加到 ~/.zshrc 或 ~/.bash_profile）
```bash
echo 'export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_351.jdk/Contents/Home' >> ~/.zshrc
source ~/.zshrc
```

### 查看所有可用的JDK版本
```bash
/usr/libexec/java_home -V
```

## 🚀 启动步骤

### 第一步：初始化数据库

确保MySQL服务已启动，然后执行初始化脚本：

```bash
# 方式1：使用mysql命令行
mysql -u root -p < src/main/resources/init.sql

# 方式2：或者手动执行SQL
mysql -u root -p
source /path/to/springcloud_09_sharding_order/src/main/resources/init.sql
```

### 第二步：修改配置文件

编辑 `src/main/resources/application.yml`，修改数据库连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/order_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: your_password  # 修改为你的MySQL密码
```

### 第三步：启动Eureka Server（如果尚未启动）

```bash
cd ../srpingcloud_01eureka_server
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_351.jdk/Contents/Home
mvn spring-boot:run
```

Eureka Server将在 http://localhost:8761 启动

### 第四步：启动订单服务

```bash
cd springcloud_09_sharding_order
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_351.jdk/Contents/Home
mvn spring-boot:run
```

订单服务将在 http://localhost:9009 启动

## ✅ 验证启动

### 1. 检查Eureka注册
访问 http://localhost:8761，确认 ORDER-SERVICE 已注册

### 2. 测试订单接口

```bash
# 批量生成100条订单数据
curl -X POST "http://localhost:9009/order/batch?count=100"

# 预期响应示例：
# {"success":true,"message":"批量创建完成，成功：100，失败：0","data":100}

# 查询订单数量
curl "http://localhost:9009/order/count"

# 预期响应示例：
# {"success":true,"data":100}

# 查询订单详情
curl "http://localhost:9009/order/1"

# 查询用户订单列表
curl "http://localhost:9009/order/user/1"

# 创建新订单
curl -X POST "http://localhost:9009/order/create" \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "receiverName": "张三",
    "receiverPhone": "13800138000",
    "receiverAddress": "北京市朝阳区"
  }'
```

## 🔍 常见问题排查

### 问题1：编译失败 - No compiler is provided
**解决方案**：设置正确的JAVA_HOME环境变量（见上文）

### 问题2：数据库连接失败
**解决方案**：
- 检查MySQL服务是否启动：`mysql.server status`
- 检查数据库是否创建：`mysql -u root -p -e "SHOW DATABASES;"`
- 检查用户名密码是否正确
- 检查端口是否正确（默认3306）

### 问题3：端口被占用
**解决方案**：
- 修改application.yml中的端口号
- 或者停止占用端口的进程：
  ```bash
  # 查找占用9009端口的进程
  lsof -i :9009
  # 杀死进程
  kill -9 <PID>
  ```

### 问题4：Eureka注册失败
**解决方案**：
- 确认Eureka Server已启动
- 检查Eureka地址配置是否正确
- 查看日志中的错误信息

## 📊 查看日志

启动成功后，可以在控制台看到类似的日志：

```
Started OrderApplication in 8.234 seconds
Registering application ORDER-SERVICE with eureka with status UP
Tomcat started on port(s): 9009 (http)
```

如果遇到问题，查看日志中的错误堆栈信息，通常能找到问题所在。

## 🎯 下一步

启动成功后，您可以：

1. **测试批量数据生成**：创建大量订单数据
2. **准备分库分表改造**：参考README.md中的改造步骤
3. **学习分库分表概念**：理解分片键、绑定表、广播表等概念

祝您学习愉快！🎉