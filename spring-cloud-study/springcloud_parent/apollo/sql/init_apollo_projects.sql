-- ============================================================
-- Apollo 项目初始化脚本 - 导入所有微服务配置
-- ============================================================

-- 1. 在 ApolloPortalDB 中创建应用
INSERT INTO ApolloPortalDB.App (AppId, Name, OrgId, OrgName, OwnerName, OwnerEmail, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
('eureka-server', 'Eureka注册中心', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('eureka-client', 'Eureka客户端', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('eureka-client02', 'Eureka客户端02', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('eureka-client01', 'Eureka客户端01', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('hystrix-client', 'Hystrix熔断客户端', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('feign-client', 'Feign调用客户端', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('hystrix-dashboard', 'Hystrix仪表盘', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('gateway-service', '网关服务', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('sharding-order', '分库分表订单服务', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo');

-- 2. 在 ApolloConfigDB 中创建应用
INSERT INTO ApolloConfigDB.App (AppId, Name, OrgId, OrgName, OwnerName, OwnerEmail, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
('eureka-server', 'Eureka注册中心', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('eureka-client', 'Eureka客户端', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('eureka-client02', 'Eureka客户端02', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('eureka-client01', 'Eureka客户端01', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('hystrix-client', 'Hystrix熔断客户端', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('feign-client', 'Feign调用客户端', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('hystrix-dashboard', 'Hystrix仪表盘', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('gateway-service', '网关服务', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo'),
('sharding-order', '分库分表订单服务', 'TEST1', '样例部门1', 'apollo', 'apollo@acme.com', 'apollo', 'apollo');

-- 3. 在两个库中创建 AppNamespace
INSERT INTO ApolloConfigDB.AppNamespace (Name, AppId, Format, IsPublic, Comment, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
('application', 'eureka-server', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'eureka-client', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'eureka-client02', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'eureka-client01', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'hystrix-client', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'feign-client', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'hystrix-dashboard', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'gateway-service', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'sharding-order', 'properties', b'0', 'default namespace', 'apollo', 'apollo');

INSERT INTO ApolloPortalDB.AppNamespace (Name, AppId, Format, IsPublic, Comment, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
('application', 'eureka-server', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'eureka-client', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'eureka-client02', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'eureka-client01', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'hystrix-client', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'feign-client', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'hystrix-dashboard', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'gateway-service', 'properties', b'0', 'default namespace', 'apollo', 'apollo'),
('application', 'sharding-order', 'properties', b'0', 'default namespace', 'apollo', 'apollo');

-- 4. 创建默认集群
INSERT INTO ApolloConfigDB.Cluster (AppId, Name, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
('eureka-server', 'default', 'apollo', 'apollo'),
('eureka-client', 'default', 'apollo', 'apollo'),
('eureka-client02', 'default', 'apollo', 'apollo'),
('eureka-client01', 'default', 'apollo', 'apollo'),
('hystrix-client', 'default', 'apollo', 'apollo'),
('feign-client', 'default', 'apollo', 'apollo'),
('hystrix-dashboard', 'default', 'apollo', 'apollo'),
('gateway-service', 'default', 'apollo', 'apollo'),
('sharding-order', 'default', 'apollo', 'apollo');

-- 5. 创建默认命名空间（注意：Namespace 表没有 Format 列）
INSERT INTO ApolloConfigDB.Namespace (AppId, ClusterName, NamespaceName, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
('eureka-server', 'default', 'application', 'apollo', 'apollo'),
('eureka-client', 'default', 'application', 'apollo', 'apollo'),
('eureka-client02', 'default', 'application', 'apollo', 'apollo'),
('eureka-client01', 'default', 'application', 'apollo', 'apollo'),
('hystrix-client', 'default', 'application', 'apollo', 'apollo'),
('feign-client', 'default', 'application', 'apollo', 'apollo'),
('hystrix-dashboard', 'default', 'application', 'apollo', 'apollo'),
('gateway-service', 'default', 'application', 'apollo', 'apollo'),
('sharding-order', 'default', 'application', 'apollo', 'apollo');

-- ============================================================
-- 6. 插入配置项 (Item)
-- ============================================================

-- ---------- eureka-server 配置 ----------
INSERT INTO ApolloConfigDB.Item (NamespaceId, `Key`, Value, Comment, LineNum, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-server' AND ClusterName='default' AND NamespaceName='application'), 'server.port', '8761', 'eureka默认端口号', 1, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-server' AND ClusterName='default' AND NamespaceName='application'), 'spring.application.name', 'EUREKASERVER', '服务名称', 2, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-server' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.inetutils.ignored-interfaces', '.*VMnet.*', '忽略VMnet网卡', 3, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-server' AND ClusterName='default' AND NamespaceName='application'), 'eureka.client.service-url.defaultZone', 'http://localhost:8761/eureka', 'eureka注册中心地址', 4, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-server' AND ClusterName='default' AND NamespaceName='application'), 'eureka.client.fetch-registry', 'false', '不拉取注册信息', 5, 'apollo', 'apollo');

-- ---------- eureka-client (02) 配置 ----------
INSERT INTO ApolloConfigDB.Item (NamespaceId, `Key`, Value, Comment, LineNum, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-client' AND ClusterName='default' AND NamespaceName='application'), 'server.port', '8989', '服务端口', 1, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-client' AND ClusterName='default' AND NamespaceName='application'), 'spring.application.name', 'EUREKACLIENT', '服务名称', 2, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-client' AND ClusterName='default' AND NamespaceName='application'), 'eureka.client.service-url.defaultZone', 'http://localhost:8761/eureka', 'eureka注册中心地址', 3, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-client' AND ClusterName='default' AND NamespaceName='application'), 'feign.httpclient.connection-timeout', '5000', 'feign连接超时', 4, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-client' AND ClusterName='default' AND NamespaceName='application'), 'feign.httpclient.ok-http.read-timeout', '5000', 'feign读取超时', 5, 'apollo', 'apollo');

-- ---------- eureka-client02 (03) 配置 ----------
INSERT INTO ApolloConfigDB.Item (NamespaceId, `Key`, Value, Comment, LineNum, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-client02' AND ClusterName='default' AND NamespaceName='application'), 'server.port', '8990', '服务端口', 1, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-client02' AND ClusterName='default' AND NamespaceName='application'), 'spring.application.name', 'PGS', '服务名称', 2, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-client02' AND ClusterName='default' AND NamespaceName='application'), 'eureka.client.service-url.defaultZone', 'http://localhost:8761/eureka', 'eureka注册中心地址', 3, 'apollo', 'apollo');

-- ---------- eureka-client01 (04) 配置 ----------
INSERT INTO ApolloConfigDB.Item (NamespaceId, `Key`, Value, Comment, LineNum, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-client01' AND ClusterName='default' AND NamespaceName='application'), 'server.port', '8991', '服务端口', 1, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-client01' AND ClusterName='default' AND NamespaceName='application'), 'spring.application.name', 'WOM', '服务名称', 2, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='eureka-client01' AND ClusterName='default' AND NamespaceName='application'), 'eureka.client.service-url.defaultZone', 'http://localhost:8761/eureka', 'eureka注册中心地址', 3, 'apollo', 'apollo');

-- ---------- hystrix-client (05) 配置 ----------
INSERT INTO ApolloConfigDB.Item (NamespaceId, `Key`, Value, Comment, LineNum, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='hystrix-client' AND ClusterName='default' AND NamespaceName='application'), 'server.port', '8992', '服务端口', 1, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='hystrix-client' AND ClusterName='default' AND NamespaceName='application'), 'spring.application.name', 'HYSTRIXCLIENT', '服务名称', 2, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='hystrix-client' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.consul.host', 'localhost', 'consul地址', 3, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='hystrix-client' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.consul.port', '8500', 'consul端口', 4, 'apollo', 'apollo');

-- ---------- feign-client (06) 配置 ----------
INSERT INTO ApolloConfigDB.Item (NamespaceId, `Key`, Value, Comment, LineNum, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='feign-client' AND ClusterName='default' AND NamespaceName='application'), 'server.port', '8993', '服务端口', 1, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='feign-client' AND ClusterName='default' AND NamespaceName='application'), 'spring.application.name', 'FEIGNCLIENT01', '服务名称', 2, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='feign-client' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.consul.host', 'localhost', 'consul地址', 3, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='feign-client' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.consul.port', '8500', 'consul端口', 4, 'apollo', 'apollo');

-- ---------- hystrix-dashboard (07) 配置 ----------
INSERT INTO ApolloConfigDB.Item (NamespaceId, `Key`, Value, Comment, LineNum, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='hystrix-dashboard' AND ClusterName='default' AND NamespaceName='application'), 'server.port', '8994', '服务端口', 1, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='hystrix-dashboard' AND ClusterName='default' AND NamespaceName='application'), 'spring.application.name', 'HYSTRIXDASHBOARD', '服务名称', 2, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='hystrix-dashboard' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.consul.host', 'localhost', 'consul地址', 3, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='hystrix-dashboard' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.consul.port', '8500', 'consul端口', 4, 'apollo', 'apollo');

-- ---------- gateway-service (08) 配置 ----------
INSERT INTO ApolloConfigDB.Item (NamespaceId, `Key`, Value, Comment, LineNum, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='gateway-service' AND ClusterName='default' AND NamespaceName='application'), 'server.port', '8995', '服务端口', 1, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='gateway-service' AND ClusterName='default' AND NamespaceName='application'), 'spring.application.name', 'GATEWAY', '服务名称', 2, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='gateway-service' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.consul.host', 'localhost', 'consul地址', 3, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='gateway-service' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.consul.port', '8500', 'consul端口', 4, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='gateway-service' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.gateway.routes[0].id', 'hystrix_router', '网关路由-hystrix', 5, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='gateway-service' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.gateway.routes[0].uri', 'http://localhost:8992', 'hystrix路由地址', 6, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='gateway-service' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.gateway.routes[0].predicates[0]', 'Path=/hystrix/**', 'hystrix路由谓词', 7, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='gateway-service' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.gateway.routes[1].id', 'feign_router', '网关路由-feign', 8, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='gateway-service' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.gateway.routes[1].uri', 'http://localhost:8993', 'feign路由地址', 9, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='gateway-service' AND ClusterName='default' AND NamespaceName='application'), 'spring.cloud.gateway.routes[1].predicates[0]', 'Path=/user/**', 'feign路由谓词', 10, 'apollo', 'apollo');

-- ---------- sharding-order (09) 配置 ----------
INSERT INTO ApolloConfigDB.Item (NamespaceId, `Key`, Value, Comment, LineNum, DataChange_CreatedBy, DataChange_LastModifiedBy) VALUES
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'server.port', '9009', '服务端口', 1, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'spring.application.name', 'order-service', '服务名称', 2, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'eureka.client.service-url.defaultZone', 'http://localhost:8761/eureka/', 'eureka注册中心地址', 3, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'eureka.instance.prefer-ip-address', 'true', '使用IP注册', 4, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'spring.datasource.driver-class-name', 'com.mysql.cj.jdbc.Driver', '数据库驱动', 5, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'spring.datasource.url', 'jdbc:mysql://localhost:3306/order_db?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai', '数据库连接URL', 6, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'spring.datasource.username', 'root', '数据库用户名', 7, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'spring.datasource.password', '12345678', '数据库密码', 8, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'spring.datasource.type', 'com.alibaba.druid.pool.DruidDataSource', '连接池类型', 9, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'spring.datasource.druid.initial-size', '5', '初始连接数', 10, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'spring.datasource.druid.min-idle', '5', '最小空闲连接数', 11, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'spring.datasource.druid.max-active', '20', '最大活跃连接数', 12, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'spring.datasource.druid.max-wait', '60000', '最大等待时间', 13, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'mybatis-plus.mapper-locations', 'classpath:mapper/*.xml', 'mapper文件位置', 14, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'mybatis-plus.type-aliases-package', 'com.zf.entity', '实体类别名包', 15, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'mybatis-plus.configuration.map-underscore-to-camel-case', 'true', '驼峰命名转换', 16, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'mybatis-plus.configuration.log-impl', 'org.apache.ibatis.logging.stdout.StdOutImpl', 'SQL日志输出', 17, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'mybatis-plus.global-config.db-config.id-type', 'auto', '主键自增', 18, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'mybatis-plus.global-config.db-config.logic-delete-field', 'deleted', '逻辑删除字段', 19, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'mybatis-plus.global-config.db-config.logic-delete-value', '1', '逻辑删除值', 20, 'apollo', 'apollo'),
((SELECT Id FROM ApolloConfigDB.Namespace WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application'), 'mybatis-plus.global-config.db-config.logic-not-delete-value', '0', '逻辑未删除值', 21, 'apollo', 'apollo');

-- ============================================================
-- 7. 发布配置 (Release) - 每个应用单独发布
-- ============================================================

-- eureka-server 发布
INSERT INTO ApolloConfigDB.Release (ReleaseKey, Name, Comment, AppId, ClusterName, NamespaceName, Configurations, IsAbandoned, DataChange_CreatedBy, DataChange_LastModifiedBy)
SELECT '202603290001', 'eureka-server-initial', '初始化导入', 'eureka-server', 'default', 'application',
(SELECT GROUP_CONCAT(CONCAT(`Key`, '=', Value) SEPARATOR '\n') FROM ApolloConfigDB.Item WHERE NamespaceId=n.Id ORDER BY LineNum),
0, 'apollo', 'apollo'
FROM ApolloConfigDB.Namespace n WHERE AppId='eureka-server' AND ClusterName='default' AND NamespaceName='application';

-- eureka-client 发布
INSERT INTO ApolloConfigDB.Release (ReleaseKey, Name, Comment, AppId, ClusterName, NamespaceName, Configurations, IsAbandoned, DataChange_CreatedBy, DataChange_LastModifiedBy)
SELECT '202603290002', 'eureka-client-initial', '初始化导入', 'eureka-client', 'default', 'application',
(SELECT GROUP_CONCAT(CONCAT(`Key`, '=', Value) SEPARATOR '\n') FROM ApolloConfigDB.Item WHERE NamespaceId=n.Id ORDER BY LineNum),
0, 'apollo', 'apollo'
FROM ApolloConfigDB.Namespace n WHERE AppId='eureka-client' AND ClusterName='default' AND NamespaceName='application';

-- eureka-client02 发布
INSERT INTO ApolloConfigDB.Release (ReleaseKey, Name, Comment, AppId, ClusterName, NamespaceName, Configurations, IsAbandoned, DataChange_CreatedBy, DataChange_LastModifiedBy)
SELECT '202603290003', 'eureka-client02-initial', '初始化导入', 'eureka-client02', 'default', 'application',
(SELECT GROUP_CONCAT(CONCAT(`Key`, '=', Value) SEPARATOR '\n') FROM ApolloConfigDB.Item WHERE NamespaceId=n.Id ORDER BY LineNum),
0, 'apollo', 'apollo'
FROM ApolloConfigDB.Namespace n WHERE AppId='eureka-client02' AND ClusterName='default' AND NamespaceName='application';

-- eureka-client01 发布
INSERT INTO ApolloConfigDB.Release (ReleaseKey, Name, Comment, AppId, ClusterName, NamespaceName, Configurations, IsAbandoned, DataChange_CreatedBy, DataChange_LastModifiedBy)
SELECT '202603290004', 'eureka-client01-initial', '初始化导入', 'eureka-client01', 'default', 'application',
(SELECT GROUP_CONCAT(CONCAT(`Key`, '=', Value) SEPARATOR '\n') FROM ApolloConfigDB.Item WHERE NamespaceId=n.Id ORDER BY LineNum),
0, 'apollo', 'apollo'
FROM ApolloConfigDB.Namespace n WHERE AppId='eureka-client01' AND ClusterName='default' AND NamespaceName='application';

-- hystrix-client 发布
INSERT INTO ApolloConfigDB.Release (ReleaseKey, Name, Comment, AppId, ClusterName, NamespaceName, Configurations, IsAbandoned, DataChange_CreatedBy, DataChange_LastModifiedBy)
SELECT '202603290005', 'hystrix-client-initial', '初始化导入', 'hystrix-client', 'default', 'application',
(SELECT GROUP_CONCAT(CONCAT(`Key`, '=', Value) SEPARATOR '\n') FROM ApolloConfigDB.Item WHERE NamespaceId=n.Id ORDER BY LineNum),
0, 'apollo', 'apollo'
FROM ApolloConfigDB.Namespace n WHERE AppId='hystrix-client' AND ClusterName='default' AND NamespaceName='application';

-- feign-client 发布
INSERT INTO ApolloConfigDB.Release (ReleaseKey, Name, Comment, AppId, ClusterName, NamespaceName, Configurations, IsAbandoned, DataChange_CreatedBy, DataChange_LastModifiedBy)
SELECT '202603290006', 'feign-client-initial', '初始化导入', 'feign-client', 'default', 'application',
(SELECT GROUP_CONCAT(CONCAT(`Key`, '=', Value) SEPARATOR '\n') FROM ApolloConfigDB.Item WHERE NamespaceId=n.Id ORDER BY LineNum),
0, 'apollo', 'apollo'
FROM ApolloConfigDB.Namespace n WHERE AppId='feign-client' AND ClusterName='default' AND NamespaceName='application';

-- hystrix-dashboard 发布
INSERT INTO ApolloConfigDB.Release (ReleaseKey, Name, Comment, AppId, ClusterName, NamespaceName, Configurations, IsAbandoned, DataChange_CreatedBy, DataChange_LastModifiedBy)
SELECT '202603290007', 'hystrix-dashboard-initial', '初始化导入', 'hystrix-dashboard', 'default', 'application',
(SELECT GROUP_CONCAT(CONCAT(`Key`, '=', Value) SEPARATOR '\n') FROM ApolloConfigDB.Item WHERE NamespaceId=n.Id ORDER BY LineNum),
0, 'apollo', 'apollo'
FROM ApolloConfigDB.Namespace n WHERE AppId='hystrix-dashboard' AND ClusterName='default' AND NamespaceName='application';

-- gateway-service 发布
INSERT INTO ApolloConfigDB.Release (ReleaseKey, Name, Comment, AppId, ClusterName, NamespaceName, Configurations, IsAbandoned, DataChange_CreatedBy, DataChange_LastModifiedBy)
SELECT '202603290008', 'gateway-service-initial', '初始化导入', 'gateway-service', 'default', 'application',
(SELECT GROUP_CONCAT(CONCAT(`Key`, '=', Value) SEPARATOR '\n') FROM ApolloConfigDB.Item WHERE NamespaceId=n.Id ORDER BY LineNum),
0, 'apollo', 'apollo'
FROM ApolloConfigDB.Namespace n WHERE AppId='gateway-service' AND ClusterName='default' AND NamespaceName='application';

-- sharding-order 发布
INSERT INTO ApolloConfigDB.Release (ReleaseKey, Name, Comment, AppId, ClusterName, NamespaceName, Configurations, IsAbandoned, DataChange_CreatedBy, DataChange_LastModifiedBy)
SELECT '202603290009', 'sharding-order-initial', '初始化导入', 'sharding-order', 'default', 'application',
(SELECT GROUP_CONCAT(CONCAT(`Key`, '=', Value) SEPARATOR '\n') FROM ApolloConfigDB.Item WHERE NamespaceId=n.Id ORDER BY LineNum),
0, 'apollo', 'apollo'
FROM ApolloConfigDB.Namespace n WHERE AppId='sharding-order' AND ClusterName='default' AND NamespaceName='application';

-- ============================================================
-- 8. 通知 ReleaseMessage（让 ConfigService 感知到新配置）
-- ============================================================
INSERT INTO ApolloConfigDB.ReleaseMessage (Message) VALUES
('eureka-server+default+application'),
('eureka-client+default+application'),
('eureka-client02+default+application'),
('eureka-client01+default+application'),
('hystrix-client+default+application'),
('feign-client+default+application'),
('hystrix-dashboard+default+application'),
('gateway-service+default+application'),
('sharding-order+default+application');