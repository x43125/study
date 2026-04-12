package com.zf.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 数据迁移记录实体
 * 
 * 说明：记录每条数据的迁移情况，用于实现幂等性和断点续传
 * 
 * @author zf
 * @since 2026-04-12
 */
@Data
public class DataMigrationRecord {
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 迁移任务ID
     */
    private Long taskId;
    
    /**
     * 订单编号
     */
    private Long orderNo;
    
    /**
     * 表名（t_order或t_order_item）
     */
    private String tableName;
    
    /**
     * 分片表名（如：t_order_202604）
     */
    private String targetTable;
    
    /**
     * 迁移状态：0-待迁移 1-迁移成功 2-迁移失败
     */
    private Integer status;
    
    /**
     * 重试次数
     */
    private Integer retryCount;
    
    /**
     * 错误信息
     */
    private String errorMsg;
    
    /**
     * 迁移时间
     */
    private LocalDateTime migrateTime;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    
    /**
     * 状态枚举
     */
    public enum Status {
        PENDING(0, "待迁移"),
        SUCCESS(1, "迁移成功"),
        FAILED(2, "迁移失败");
        
        private final int code;
        private final String desc;
        
        Status(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        
        public int getCode() {
            return code;
        }
        
        public String getDesc() {
            return desc;
        }
    }
}