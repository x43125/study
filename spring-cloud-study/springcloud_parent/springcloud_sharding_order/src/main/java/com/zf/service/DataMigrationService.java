package com.zf.service;

import java.util.List;

import com.zf.dto.MigrationTaskDTO;

/**
 * 数据迁移服务接口
 * 
 * 功能：
 * 1. 创建和管理迁移任务
 * 2. 执行数据迁移（按月分批）
 * 3. 支持断点续传
 * 4. 支持失败重试
 * 5. 支持数据校验
 * 6. 支持回滚
 * 
 * @author zf
 * @since 2026-04-12
 */
public interface DataMigrationService {
    
    /**
     * 创建迁移任务
     * 
     * @param taskName 任务名称
     * @param tableName 表名（t_order或t_order_item）
     * @param startMonth 起始月份（202510）
     * @param endMonth 结束月份（202610）
     * @return 任务ID
     */
    Long createTask(String taskName, String tableName, String startMonth, String endMonth);
    
    /**
     * 执行迁移任务
     * 
     * @param taskId 任务ID
     */
    void executeMigration(Long taskId);
    
    /**
     * 异步执行迁移任务
     * 
     * @param taskId 任务ID
     */
    void executeMigrationAsync(Long taskId);
    
    /**
     * 查询任务信息
     * 
     * @param taskId 任务ID
     * @return 任务信息
     */
    MigrationTaskDTO getTaskInfo(Long taskId);
    
    /**
     * 查询所有任务
     * 
     * @return 任务列表
     */
    List<MigrationTaskDTO> getAllTasks();
    
    /**
     * 查询指定状态的任务
     * 
     * @param status 状态
     * @return 任务列表
     */
    List<MigrationTaskDTO> getTasksByStatus(Integer status);
    
    /**
     * 按月迁移数据
     * 
     * @param taskId 任务ID
     * @param tableName 表名
     * @param month 月份（202604）
     */
    void migrateByMonth(Long taskId, String tableName, String month);
    
    /**
     * 分批迁移数据
     * 
     * @param taskId 任务ID
     * @param tableName 表名
     * @param month 月份
     * @param offset 偏移量
     * @param limit 批次大小
     * @return 迁移数量
     */
    int migrateBatch(Long taskId, String tableName, String month, int offset, int limit);
    
    /**
     * 数据校验
     * 
     * @param taskId 任务ID
     * @return 校验是否通过
     */
    boolean validateData(Long taskId);
    
    /**
     * 回滚迁移
     * 
     * @param taskId 任务ID
     */
    void rollbackMigration(Long taskId);
    
    /**
     * 暂停迁移任务
     * 
     * @param taskId 任务ID
     */
    void pauseTask(Long taskId);
    
    /**
     * 继续迁移任务
     * 
     * @param taskId 任务ID
     */
    void resumeTask(Long taskId);
    
    /**
     * 取消迁移任务
     * 
     * @param taskId 任务ID
     */
    void cancelTask(Long taskId);
    
    /**
     * 删除迁移任务
     * 
     * @param taskId 任务ID
     */
    void deleteTask(Long taskId);
}