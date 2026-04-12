# 订单服务 - 分库分表改造指南

## 📋 项目说明

这是一个完整的订单微服务项目，用于演示分库分表的完整过程。当前版本是**单库单表**版本，您可以直接在此基础上进行分库分表改造，对比学习效果更佳。

## 🎯 项目特点

1. **标准微服务架构**：注册到Eureka，完整的Service-Controller-Mapper三层架构
2. **完整的业务场景**：订单、订单明细、用户、区域字典
3. **丰富的接口**：CRUD、批量数据生成、统计查询
4. **详细的改造提示**：代码中包含了分库分表改造的详细注释

## 📊 数据库设计

### 当前架构（单库单表）
```
order_db (单一数据库)
├── t_user          # 用户表（10条数据）
├── t_region        # 区域字典表（17条数据）
├── t_order         # 订单表
└── t_order_item    # 订单明细表
```

### 建议的分库分表架构
```
order_db_0 (分库0)                    order_db_1 (分库1)
├── t_user              # 单表          └── t_user              # 单表（或独立用户库）
├── t_region            # 广播表         └── t_region            # 广播表
├── t_order_0           # 分表0           └── t_order_0           # 分表0
├── t_order_1           # 分表1           └── t_order_1           # 分表1
├── t_order_item_0      # 分表0           └── t_order_item_0      # 分表0
└── t_order_item_1      # 分表1           └── t_order_item_1      # 分表1
```

## 🚀 快速开始

### 1. 初始化数据库

```bash
# 执行SQL脚本
mysql -u root -p < src/main/resources/init.sql
```

### 2. 修改配置

修改 `src/main/resources/application.yml` 中的数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/order_db?...
    username: root
    password: your_password
```

### 3. 启动项目

```bash
# 确保Eureka Server已启动
cd srpingcloud_01eureka_server
mvn spring-boot:run

# 启动订单服务
cd springcloud_09_sharding_order
mvn spring-boot:run
```

### 4. 测试接口

```bash
# 批量生成100条订单数据
curl -X POST "http://localhost:9009/order/batch?count=100"

# 查询订单详情
curl "http://localhost:9009/order/1"

# 查询用户订单
curl "http://localhost:9009/order/user/1"

# 统计订单数量
curl "http://localhost:9009/order/count"
```

## 🔧 分库分表改造步骤

### 第一步：添加ShardingSphere依赖

在 `pom.xml` 中添加：

```xml
<dependency>
    <groupId>org.apache.shardingsphere</groupId>
    <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
    <version>4.1.1</version>
</dependency>
```

### 第二步：创建分库分表

创建两个数据库：
```sql
CREATE DATABASE order_db_0;
CREATE DATABASE order_db_1;
```

在每个数据库中创建对应的表结构（参考init.sql）。

### 第三步：配置ShardingSphere

修改 `application.yml`，添加ShardingSphere配置：

```yaml
spring:
  shardingsphere:
    datasource:
      names: ds0,ds1
      ds0:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/order_db_0?...
      ds1:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/order_db_1?...
    
    sharding:
      default-data-source-name: ds0  # 未配置分片的表使用默认数据源
      
      tables:
        t_order:
          actual-data-nodes: ds$->{0..1}.t_order_$->{0..1}
          database-strategy:
            inline:
              sharding-column: user_id
              algorithm-expression: ds$->{user_id % 2}
          table-strategy:
            inline:
              sharding-column: id
              algorithm-expression: t_order_$->{id % 2}
          
        t_order_item:
          actual-data-nodes: ds$->{0..1}.t_order_item_$->{0..1}
          database-strategy:
            inline:
              sharding-column: order_id
              algorithm-expression: ds$->{order_id % 2}
          table-strategy:
            inline:
              sharding-column: order_id
              algorithm-expression: t_order_item_$->{order_id % 2}
      
      binding-tables:
        - t_order,t_order_item
      
      broadcast-tables:
        - t_region
      
      key-generators:
        snowflake:
          type: SNOWFLAKE
```

### 第四步：测试验证

1. 插入数据，观察数据分布
2. 查询数据，验证路由正确性
3. 关联查询，验证绑定表效果

## 📝 改造注意事项

### 1. 分片键选择
- **订单表（t_order）**：
  - 分库键：`user_id`（按用户查询是常见场景）
  - 分表键：`id`（订单ID是唯一标识）

- **订单明细表（t_order_item）**：
  - 分片键：`order_id`（与订单表保持一致）

### 2. 绑定表配置
- 将 `t_order` 和 `t_order_item` 配置为绑定表
- 确保同一订单的订单明细和订单在同一个分库分表中
- 避免跨库关联查询

### 3. 广播表配置
- `t_region` 适合作为广播表
- 每个分库中都存在，数据完全相同
- 减少跨库关联查询

### 4. ID生成策略
- 分库分表后，不能使用数据库自增ID
- 建议使用雪花算法（Snowflake）
- 在配置中启用全局ID生成器

### 5. 事务管理
- 单库事务：使用 `@Transactional`
- 跨库事务：需要分布式事务（如Seata）
- 业务层要注意事务边界

### 6. 查询优化
- 尽量使用分片键查询
- 避免跨库关联查询
- 统计查询考虑在业务层聚合

## 🔍 关键代码位置

### 实体类（包含改造提示）
- `Order.java` - 订单实体（包含分片键说明）
- `OrderItem.java` - 订单明细实体（包含绑定表说明）
- `Region.java` - 区域实体（包含广播表说明）

### Service层（包含事务说明）
- `OrderServiceImpl.java` - 订单业务实现（包含分布式事务说明）

### Mapper接口
- `OrderMapper.java` - 订单Mapper
- `OrderItemMapper.java` - 订单明细Mapper
- `UserMapper.java` - 用户Mapper
- `RegionMapper.java` - 区域Mapper

### 控制器（包含测试接口）
- `OrderController.java` - 订单控制器（包含批量数据生成接口）

## 📊 测试场景建议

### 1. 数据分布测试
```bash
# 批量插入1000条订单
curl -X POST "http://localhost:9009/order/batch?count=1000"

# 查询每个库的数据分布
# 验证数据是否均匀分布
```

### 2. 路由正确性测试
```bash
# 按订单ID查询
curl "http://localhost:9009/order/1"

# 按用户ID查询
curl "http://localhost:9009/order/user/1"

# 验证查询是否路由到正确的分库分表
```

### 3. 绑定表测试
```bash
# 查询订单详情（含明细）
curl "http://localhost:9009/order/1"

# 验证订单和明细是否在同一分库分表
```

### 4. 广播表测试
```bash
# 查询区域信息
curl "http://localhost:9009/region/list"

# 验证广播表在每个分库中都存在
```

## 🎓 学习路径

1. **第一阶段**：运行当前的单库单表版本，熟悉业务逻辑
2. **第二阶段**：按照改造步骤，逐步完成分库分表配置
3. **第三阶段**：测试验证，观察数据分布和查询性能
4. **第四阶段**：深入理解分片策略、绑定表、广播表等概念

## 📚 参考资源

- [ShardingSphere官方文档](https://shardingsphere.apache.org/document/legacy/4.x/document/cn/manual/)
- [MyBatis-Plus官方文档](https://baomidou.com/)
- [Spring Cloud官方文档](https://spring.io/projects/spring-cloud)

## 💡 常见问题

### Q1: 如何选择分片键？
A: 选择查询频率高、数据分布均匀的字段。订单表通常使用用户ID或订单ID。

### Q2: 如何处理跨库事务？
A: 使用分布式事务框架，如Seata。或者在业务层避免跨库操作。

### Q3: 如何优化跨库查询？
A: 使用绑定表、广播表，或者在应用层聚合查询结果。

### Q4: 分片数量如何选择？
A: 根据数据量和查询压力决定。通常从2-4个分库开始，每个分库2-4张表。

## 📞 支持

如有问题，请查看代码中的详细注释或参考官方文档。