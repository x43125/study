package com.zf.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 数据迁移任务实体
 * 
 * 说明：记录每次数据迁移任务的信息和状态
 * 
 * @author zf
 * @since 2026-04-12
 */
@Data
public class DataMigrationTask {
    
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 任务名称（如：202510月订单迁移）
     */
    private String taskName;
    
    /**
     * 表名（t_order或t_order_item）
     */
    private String tableName;
    
    /**
     * 起始月份（202510）
     */
    private String startMonth;
    
    /**
     * 结束月份（202610）
     */
    private String endMonth;
    
    /**
     * 状态：0-待执行 1-执行中 2-成功 3-失败
     */
    private Integer status;
    
    /**
     * 已迁移数量
     */
    private Long migratedCount;
    
    /**
     * 总数量
     */
    private Long totalCount;
    
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    
    /**
     * 错误信息
     */
    private String errorMsg;
    
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
        PENDING(0, "待执行"),
        RUNNING(1, "执行中"),
        SUCCESS(2, "成功"),
        FAILED(3, "失败");
        
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
