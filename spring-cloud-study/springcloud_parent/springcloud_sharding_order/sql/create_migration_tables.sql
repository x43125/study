-- =============================================
-- 数据迁移管理表创建脚本
-- 创建时间：2026-04-12
-- 用途：管理订单表分库分表的数据迁移任务
-- =============================================

-- =============================================
-- 表1：数据迁移任务表
-- 说明：记录每次数据迁移任务的信息和状态
-- =============================================
CREATE TABLE IF NOT EXISTS `t_data_migration_task` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `task_name` varchar(100) NOT NULL COMMENT '任务名称（如：202510月订单迁移）',
  `table_name` varchar(50) NOT NULL COMMENT '表名（t_order或t_order_item）',
  `start_month` varchar(7) NOT NULL COMMENT '起始月份（202510）',
  `end_month` varchar(7) NOT NULL COMMENT '结束月份（202610）',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-待执行 1-执行中 2-成功 3-失败',
  `migrated_count` bigint NOT NULL DEFAULT '0' COMMENT '已迁移数量',
  `total_count` bigint NOT NULL DEFAULT '0' COMMENT '总数量',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `error_msg` text COMMENT '错误信息',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_task_name` (`task_name`),
  KEY `idx_table_name` (`table_name`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据迁移任务表';

-- =============================================
-- 表2：数据迁移记录表
-- 说明：记录每条订单的迁移情况，支持幂等性和断点续传
-- =============================================
CREATE TABLE IF NOT EXISTS `t_data_migration_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `task_id` bigint NOT NULL COMMENT '任务ID（关联t_data_migration_task）',
  `order_no` bigint NOT NULL COMMENT '订单号',
  `table_name` varchar(50) NOT NULL COMMENT '表名（t_order或t_order_item）',
  `migrate_status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-失败 1-成功',
  `old_table_name` varchar(50) DEFAULT NULL COMMENT '老表名称',
  `new_table_name` varchar(50) DEFAULT NULL COMMENT '新表名称',
  `error_msg` text COMMENT '错误信息',
  `retry_times` int NOT NULL DEFAULT '0' COMMENT '重试次数',
  `migrate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '迁移时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_task_order` (`task_id`, `order_no`),
  KEY `idx_order_no` (`order_no`),
  KEY `idx_task_id` (`task_id`),
  KEY `idx_status` (`migrate_status`),
  KEY `idx_table_name` (`table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据迁移记录表';

-- =============================================
-- 初始化数据示例（可选）
-- =============================================

-- 示例：创建一个迁移任务
-- INSERT INTO t_data_migration_task (
--   task_name, table_name, start_month, end_month, status, 
--   migrated_count, total_count, start_time
-- ) VALUES (
--   '202510月订单表迁移', 't_order', '202510', '202510', 1, 0, 0, NOW()
-- );

-- =============================================
-- 验证表是否创建成功
-- =============================================
SELECT 't_data_migration_task表创建状态' as check_item, 
       COUNT(*) as table_exists 
FROM information_schema.tables 
WHERE table_schema = DATABASE() 
  AND table_name = 't_data_migration_task';

SELECT 't_data_migration_record表创建状态' as check_item, 
       COUNT(*) as table_exists 
FROM information_schema.tables 
WHERE table_schema = DATABASE() 
  AND table_name = 't_data_migration_record';