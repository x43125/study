-- =============================================
-- 订单服务数据库初始化脚本（分表版本）
-- 说明：
--   1. 不分库，单数据库 order_db
--   2. 按月分表，code（雪花算法）作为分片键
--   3. 从 code 时间戳提取月份，路由到对应月份的分表
--   4. t_order_default：历史数据兜底表
--   5. t_order_202601 ~ t_order_202612：按月分表
-- =============================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS order_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE order_db;

-- =============================================
-- 1. 用户表（t_user）
-- 说明：用户表通常不需要分库分表，数据量相对较小
-- =============================================
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    nickname VARCHAR(50) COMMENT '昵称',
    real_name VARCHAR(50) COMMENT '真实姓名',
    phone VARCHAR(20) COMMENT '手机号',
    email VARCHAR(100) COMMENT '邮箱',
    avatar VARCHAR(255) COMMENT '头像URL',
    status TINYINT DEFAULT 0 COMMENT '用户状态：0-正常 1-禁用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_phone (phone),
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- =============================================
-- 2. 区域字典表（t_region）
-- 说明：适合作为广播表，在每个分库中都存在
-- =============================================
DROP TABLE IF EXISTS t_region;
CREATE TABLE t_region (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '区域ID',
    code VARCHAR(20) NOT NULL UNIQUE COMMENT '区域代码',
    name VARCHAR(50) NOT NULL COMMENT '区域名称',
    parent_id BIGINT COMMENT '父级区域ID',
    level TINYINT COMMENT '区域级别：1-省/直辖市 2-市 3-区/县',
    sort INT DEFAULT 0 COMMENT '排序值',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_parent_id (parent_id),
    INDEX idx_code (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='区域字典表';

-- 插入区域数据示例
INSERT INTO t_region (code, name, parent_id, level, sort) VALUES
('110000', '北京市', NULL, 1, 1),
('110100', '市辖区', 1, 2, 1),
('110101', '东城区', 2, 3, 1),
('110102', '西城区', 2, 3, 2),
('110105', '朝阳区', 2, 3, 3),
('310000', '上海市', NULL, 1, 2),
('310100', '市辖区', 6, 2, 1),
('310101', '黄浦区', 7, 3, 1),
('310104', '徐汇区', 7, 3, 2),
('310105', '长宁区', 7, 3, 3),
('440000', '广东省', NULL, 1, 3),
('440100', '广州市', 10, 2, 1),
('440103', '荔湾区', 11, 3, 1),
('440104', '越秀区', 11, 3, 2),
('440300', '深圳市', 10, 2, 2),
('440304', '福田区', 14, 3, 1),
('440305', '南山区', 14, 3, 2);

-- =============================================
-- 3. 订单表（t_order）- 历史数据兜底表
-- 说明：存放无法按月份路由的历史数据
-- =============================================
DROP TABLE IF EXISTS t_order;
CREATE TABLE t_order (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) COMMENT '用户昵称（冗余字段）',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消',
    address_id BIGINT COMMENT '收货地址ID',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address VARCHAR(255) COMMENT '收货地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    finish_time DATETIME COMMENT '完成时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_user_id (user_id) COMMENT '用户ID索引',
    INDEX idx_order_no (order_no) COMMENT '订单编号索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表（默认/历史兜底表）';

-- =============================================
-- 3.1 订单分表 - 2026年1月
-- =============================================
DROP TABLE IF EXISTS t_order_202601;
CREATE TABLE t_order_202601 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) COMMENT '用户昵称（冗余字段）',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消',
    address_id BIGINT COMMENT '收货地址ID',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address VARCHAR(255) COMMENT '收货地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    finish_time DATETIME COMMENT '完成时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_user_id (user_id) COMMENT '用户ID索引',
    INDEX idx_order_no (order_no) COMMENT '订单编号索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表-2026年1月';

-- =============================================
-- 3.2 订单分表 - 2026年2月
-- =============================================
DROP TABLE IF EXISTS t_order_202602;
CREATE TABLE t_order_202602 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) COMMENT '用户昵称（冗余字段）',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消',
    address_id BIGINT COMMENT '收货地址ID',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address VARCHAR(255) COMMENT '收货地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    finish_time DATETIME COMMENT '完成时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_user_id (user_id) COMMENT '用户ID索引',
    INDEX idx_order_no (order_no) COMMENT '订单编号索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表-2026年2月';

-- =============================================
-- 3.3 订单分表 - 2026年3月
-- =============================================
DROP TABLE IF EXISTS t_order_202603;
CREATE TABLE t_order_202603 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) COMMENT '用户昵称（冗余字段）',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消',
    address_id BIGINT COMMENT '收货地址ID',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address VARCHAR(255) COMMENT '收货地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    finish_time DATETIME COMMENT '完成时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_user_id (user_id) COMMENT '用户ID索引',
    INDEX idx_order_no (order_no) COMMENT '订单编号索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表-2026年3月';

-- =============================================
-- 3.4 订单分表 - 2026年4月
-- =============================================
DROP TABLE IF EXISTS t_order_202604;
CREATE TABLE t_order_202604 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) COMMENT '用户昵称（冗余字段）',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消',
    address_id BIGINT COMMENT '收货地址ID',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address VARCHAR(255) COMMENT '收货地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    finish_time DATETIME COMMENT '完成时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_user_id (user_id) COMMENT '用户ID索引',
    INDEX idx_order_no (order_no) COMMENT '订单编号索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表-2026年4月';

-- =============================================
-- 3.5 订单分表 - 2026年5月
-- =============================================
DROP TABLE IF EXISTS t_order_202605;
CREATE TABLE t_order_202605 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) COMMENT '用户昵称（冗余字段）',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消',
    address_id BIGINT COMMENT '收货地址ID',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address VARCHAR(255) COMMENT '收货地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    finish_time DATETIME COMMENT '完成时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_user_id (user_id) COMMENT '用户ID索引',
    INDEX idx_order_no (order_no) COMMENT '订单编号索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表-2026年5月';

-- =============================================
-- 3.6 订单分表 - 2026年6月
-- =============================================
DROP TABLE IF EXISTS t_order_202606;
CREATE TABLE t_order_202606 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) COMMENT '用户昵称（冗余字段）',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消',
    address_id BIGINT COMMENT '收货地址ID',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address VARCHAR(255) COMMENT '收货地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    finish_time DATETIME COMMENT '完成时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_user_id (user_id) COMMENT '用户ID索引',
    INDEX idx_order_no (order_no) COMMENT '订单编号索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表-2026年6月';

-- =============================================
-- 3.7 订单分表 - 2026年7月
-- =============================================
DROP TABLE IF EXISTS t_order_202607;
CREATE TABLE t_order_202607 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) COMMENT '用户昵称（冗余字段）',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消',
    address_id BIGINT COMMENT '收货地址ID',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address VARCHAR(255) COMMENT '收货地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    finish_time DATETIME COMMENT '完成时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_user_id (user_id) COMMENT '用户ID索引',
    INDEX idx_order_no (order_no) COMMENT '订单编号索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表-2026年7月';

-- =============================================
-- 3.8 订单分表 - 2026年8月
-- =============================================
DROP TABLE IF EXISTS t_order_202608;
CREATE TABLE t_order_202608 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) COMMENT '用户昵称（冗余字段）',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消',
    address_id BIGINT COMMENT '收货地址ID',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address VARCHAR(255) COMMENT '收货地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    finish_time DATETIME COMMENT '完成时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_user_id (user_id) COMMENT '用户ID索引',
    INDEX idx_order_no (order_no) COMMENT '订单编号索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表-2026年8月';

-- =============================================
-- 3.9 订单分表 - 2026年9月
-- =============================================
DROP TABLE IF EXISTS t_order_202609;
CREATE TABLE t_order_202609 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) COMMENT '用户昵称（冗余字段）',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消',
    address_id BIGINT COMMENT '收货地址ID',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address VARCHAR(255) COMMENT '收货地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    finish_time DATETIME COMMENT '完成时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_user_id (user_id) COMMENT '用户ID索引',
    INDEX idx_order_no (order_no) COMMENT '订单编号索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表-2026年9月';

-- =============================================
-- 3.10 订单分表 - 2026年10月
-- =============================================
DROP TABLE IF EXISTS t_order_202610;
CREATE TABLE t_order_202610 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) COMMENT '用户昵称（冗余字段）',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消',
    address_id BIGINT COMMENT '收货地址ID',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address VARCHAR(255) COMMENT '收货地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    finish_time DATETIME COMMENT '完成时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_user_id (user_id) COMMENT '用户ID索引',
    INDEX idx_order_no (order_no) COMMENT '订单编号索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表-2026年10月';

-- =============================================
-- 3.11 订单分表 - 2026年11月
-- =============================================
DROP TABLE IF EXISTS t_order_202611;
CREATE TABLE t_order_202611 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) COMMENT '用户昵称（冗余字段）',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消',
    address_id BIGINT COMMENT '收货地址ID',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address VARCHAR(255) COMMENT '收货地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    finish_time DATETIME COMMENT '完成时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_user_id (user_id) COMMENT '用户ID索引',
    INDEX idx_order_no (order_no) COMMENT '订单编号索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表-2026年11月';

-- =============================================
-- 3.12 订单分表 - 2026年12月
-- =============================================
DROP TABLE IF EXISTS t_order_202612;
CREATE TABLE t_order_202612 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_no VARCHAR(50) NOT NULL UNIQUE COMMENT '订单编号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    user_name VARCHAR(50) COMMENT '用户昵称（冗余字段）',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    status TINYINT DEFAULT 0 COMMENT '订单状态：0-待支付 1-已支付 2-已发货 3-已完成 4-已取消',
    address_id BIGINT COMMENT '收货地址ID',
    receiver_name VARCHAR(50) COMMENT '收货人姓名',
    receiver_phone VARCHAR(20) COMMENT '收货人电话',
    receiver_address VARCHAR(255) COMMENT '收货地址',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    pay_time DATETIME COMMENT '支付时间',
    ship_time DATETIME COMMENT '发货时间',
    finish_time DATETIME COMMENT '完成时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_user_id (user_id) COMMENT '用户ID索引',
    INDEX idx_order_no (order_no) COMMENT '订单编号索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表-2026年12月';

-- =============================================
-- 4. 订单明细表（t_order_item）- 历史数据兜底表
-- 说明：与Order表配置为绑定表（Binding Table）
-- =============================================
DROP TABLE IF EXISTS t_order_item;
CREATE TABLE t_order_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT COMMENT '商品ID',
    product_name VARCHAR(100) COMMENT '商品名称（冗余字段）',
    product_img VARCHAR(255) COMMENT '商品图片URL（冗余字段）',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    create_time BIGINT COMMENT '创建时间',
    update_time BIGINT COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_order_id (order_id) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表（默认/历史兜底表）';

-- =============================================
-- 4.1 订单明细分表 - 2026年1月
-- =============================================
DROP TABLE IF EXISTS t_order_item_202601;
CREATE TABLE t_order_item_202601 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT COMMENT '商品ID',
    product_name VARCHAR(100) COMMENT '商品名称（冗余字段）',
    product_img VARCHAR(255) COMMENT '商品图片URL（冗余字段）',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    create_time BIGINT COMMENT '创建时间',
    update_time BIGINT COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_order_id (order_id) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表-2026年1月';

-- =============================================
-- 4.2 订单明细分表 - 2026年2月
-- =============================================
DROP TABLE IF EXISTS t_order_item_202602;
CREATE TABLE t_order_item_202602 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT COMMENT '商品ID',
    product_name VARCHAR(100) COMMENT '商品名称（冗余字段）',
    product_img VARCHAR(255) COMMENT '商品图片URL（冗余字段）',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    create_time BIGINT COMMENT '创建时间',
    update_time BIGINT COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_order_id (order_id) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表-2026年2月';

-- =============================================
-- 4.3 订单明细分表 - 2026年3月
-- =============================================
DROP TABLE IF EXISTS t_order_item_202603;
CREATE TABLE t_order_item_202603 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT COMMENT '商品ID',
    product_name VARCHAR(100) COMMENT '商品名称（冗余字段）',
    product_img VARCHAR(255) COMMENT '商品图片URL（冗余字段）',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    create_time BIGINT COMMENT '创建时间',
    update_time BIGINT COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_order_id (order_id) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表-2026年3月';

-- =============================================
-- 4.4 订单明细分表 - 2026年4月
-- =============================================
DROP TABLE IF EXISTS t_order_item_202604;
CREATE TABLE t_order_item_202604 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT COMMENT '商品ID',
    product_name VARCHAR(100) COMMENT '商品名称（冗余字段）',
    product_img VARCHAR(255) COMMENT '商品图片URL（冗余字段）',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    create_time BIGINT COMMENT '创建时间',
    update_time BIGINT COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_order_id (order_id) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表-2026年4月';

-- =============================================
-- 4.5 订单明细分表 - 2026年5月
-- =============================================
DROP TABLE IF EXISTS t_order_item_202605;
CREATE TABLE t_order_item_202605 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT COMMENT '商品ID',
    product_name VARCHAR(100) COMMENT '商品名称（冗余字段）',
    product_img VARCHAR(255) COMMENT '商品图片URL（冗余字段）',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    create_time BIGINT COMMENT '创建时间',
    update_time BIGINT COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_order_id (order_id) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表-2026年5月';

-- =============================================
-- 4.6 订单明细分表 - 2026年6月
-- =============================================
DROP TABLE IF EXISTS t_order_item_202606;
CREATE TABLE t_order_item_202606 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT COMMENT '商品ID',
    product_name VARCHAR(100) COMMENT '商品名称（冗余字段）',
    product_img VARCHAR(255) COMMENT '商品图片URL（冗余字段）',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    create_time BIGINT COMMENT '创建时间',
    update_time BIGINT COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_order_id (order_id) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表-2026年6月';

-- =============================================
-- 4.7 订单明细分表 - 2026年7月
-- =============================================
DROP TABLE IF EXISTS t_order_item_202607;
CREATE TABLE t_order_item_202607 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT COMMENT '商品ID',
    product_name VARCHAR(100) COMMENT '商品名称（冗余字段）',
    product_img VARCHAR(255) COMMENT '商品图片URL（冗余字段）',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    create_time BIGINT COMMENT '创建时间',
    update_time BIGINT COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_order_id (order_id) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表-2026年7月';

-- =============================================
-- 4.8 订单明细分表 - 2026年8月
-- =============================================
DROP TABLE IF EXISTS t_order_item_202608;
CREATE TABLE t_order_item_202608 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT COMMENT '商品ID',
    product_name VARCHAR(100) COMMENT '商品名称（冗余字段）',
    product_img VARCHAR(255) COMMENT '商品图片URL（冗余字段）',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    create_time BIGINT COMMENT '创建时间',
    update_time BIGINT COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_order_id (order_id) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表-2026年8月';

-- =============================================
-- 4.9 订单明细分表 - 2026年9月
-- =============================================
DROP TABLE IF EXISTS t_order_item_202609;
CREATE TABLE t_order_item_202609 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT COMMENT '商品ID',
    product_name VARCHAR(100) COMMENT '商品名称（冗余字段）',
    product_img VARCHAR(255) COMMENT '商品图片URL（冗余字段）',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    create_time BIGINT COMMENT '创建时间',
    update_time BIGINT COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_order_id (order_id) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表-2026年9月';

-- =============================================
-- 4.10 订单明细分表 - 2026年10月
-- =============================================
DROP TABLE IF EXISTS t_order_item_202610;
CREATE TABLE t_order_item_202610 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT COMMENT '商品ID',
    product_name VARCHAR(100) COMMENT '商品名称（冗余字段）',
    product_img VARCHAR(255) COMMENT '商品图片URL（冗余字段）',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    create_time BIGINT COMMENT '创建时间',
    update_time BIGINT COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_order_id (order_id) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表-2026年10月';

-- =============================================
-- 4.11 订单明细分表 - 2026年11月
-- =============================================
DROP TABLE IF EXISTS t_order_item_202611;
CREATE TABLE t_order_item_202611 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT COMMENT '商品ID',
    product_name VARCHAR(100) COMMENT '商品名称（冗余字段）',
    product_img VARCHAR(255) COMMENT '商品图片URL（冗余字段）',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    create_time BIGINT COMMENT '创建时间',
    update_time BIGINT COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_order_id (order_id) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表-2026年11月';

-- =============================================
-- 4.12 订单明细分表 - 2026年12月
-- =============================================
DROP TABLE IF EXISTS t_order_item_202612;
CREATE TABLE t_order_item_202612 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    code BIGINT COMMENT '订单编码（雪花算法，分片键）',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    product_id BIGINT COMMENT '商品ID',
    product_name VARCHAR(100) COMMENT '商品名称（冗余字段）',
    product_img VARCHAR(255) COMMENT '商品图片URL（冗余字段）',
    price DECIMAL(10,2) NOT NULL COMMENT '商品单价',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    create_time BIGINT COMMENT '创建时间',
    update_time BIGINT COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    INDEX idx_code (code) COMMENT '订单编码索引（分片键）',
    INDEX idx_order_id (order_id) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表-2026年12月';

-- =============================================
-- 初始化用户数据
-- =============================================
INSERT INTO t_user (username, password, nickname, real_name, phone, email, status) VALUES
('user1', '123456', '用户1', '张三', '13800000001', 'user1@example.com', 0),
('user2', '123456', '用户2', '李四', '13800000002', 'user2@example.com', 0),
('user3', '123456', '用户3', '王五', '13800000003', 'user3@example.com', 0),
('user4', '123456', '用户4', '赵六', '13800000004', 'user4@example.com', 0),
('user5', '123456', '用户5', '钱七', '13800000005', 'user5@example.com', 0),
('user6', '123456', '用户6', '孙八', '13800000006', 'user6@example.com', 0),
('user7', '123456', '用户7', '周九', '13800000007', 'user7@example.com', 0),
('user8', '123456', '用户8', '吴十', '13800000008', 'user8@example.com', 0),
('user9', '123456', '用户9', '郑十一', '13800000009', 'user9@example.com', 0),
('user10', '123456', '用户10', '王十二', '13800000010', 'user10@example.com', 0);

-- =============================================
-- 创建说明
-- =============================================
SELECT '数据库初始化完成！' AS message;
SELECT '数据库名: order_db' AS info;
SELECT '表数量: 4' AS info;
SELECT '1. t_user - 用户表' AS table_name;
SELECT '2. t_region - 区域字典表（适合广播表）' AS table_name;
SELECT '3. t_order - 订单表（适合分库分表）' AS table_name;
SELECT '4. t_order_item - 订单明细表（适合绑定表）' AS table_name;
SELECT '用户数量: 10' AS user_info;