package com.zf.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 迁移任务DTO
 * 
 * @author zf
 * @since 2026-04-12
 */
@Data
public class MigrationTaskDTO {
    
    /**
     * 任务ID
     */
    private Long id;
    
    /**
     * 任务名称
     */
    private String taskName;
    
    /**
     * 表名
     */
    private String tableName;
    
    /**
     * 起始月份
     */
    private String startMonth;
    
    /**
     * 结束月份
     */
    private String endMonth;
    
    /**
     * 状态：0-待执行 1-执行中 2-成功 3-失败
     */
    private Integer status;
    
    /**
     * 状态描述
     */
    private String statusDesc;
    
    /**
     * 已迁移数量
     */
    private Long migratedCount;
    
    /**
     * 总数量
     */
    private Long totalCount;
    
    /**
     * 迁移进度（百分比）
     */
    private Double progress;
    
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
     * 耗时（秒）
     */
    private Long duration;
    
    /**
     * 计算进度百分比
     */
    public void calculateProgress() {
        if (totalCount != null && totalCount > 0 && migratedCount != null) {
            this.progress = (double) migratedCount / totalCount * 100;
            this.progress = Math.round(this.progress * 100.0) / 100.0; // 保留两位小数
        }
    }
    
    /**
     * 计算耗时
     */
    public void calculateDuration() {
        if (startTime != null) {
            LocalDateTime endTime = this.endTime != null ? this.endTime : LocalDateTime.now();
            this.duration = java.time.Duration.between(startTime, endTime).getSeconds();
        }
    }
}