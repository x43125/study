package com.zf.mapper;

import com.zf.entity.DataMigrationRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据迁移记录Mapper接口
 * 
 * @author zf
 * @since 2026-04-12
 */
@Mapper
public interface DataMigrationRecordMapper {
    
    /**
     * 插入迁移记录
     * 
     * @param record 迁移记录
     * @return 影响行数
     */
    int insert(DataMigrationRecord record);
    
    /**
     * 批量插入迁移记录
     * 
     * @param records 迁移记录列表
     * @return 影响行数
     */
    int batchInsert(@Param("records") List<DataMigrationRecord> records);
    
    /**
     * 根据任务ID和订单号查询迁移记录
     * 
     * @param taskId 任务ID
     * @param orderNo 订单号
     * @return 迁移记录
     */
    DataMigrationRecord selectByTaskIdAndOrderNo(@Param("taskId") Long taskId,
                                                   @Param("orderNo") Long orderNo);
    
    /**
     * 根据任务ID查询迁移记录
     * 
     * @param taskId 任务ID
     * @return 迁移记录列表
     */
    List<DataMigrationRecord> selectByTaskId(@Param("taskId") Long taskId);
    
    /**
     * 根据任务ID和状态查询迁移记录
     * 
     * @param taskId 任务ID
     * @param status 状态
     * @return 迁移记录列表
     */
    List<DataMigrationRecord> selectByTaskIdAndStatus(@Param("taskId") Long taskId,
                                                        @Param("status") Integer status);
    
    /**
     * 统计任务的成功记录数
     * 
     * @param taskId 任务ID
     * @return 成功记录数
     */
    int countSuccessByTaskId(@Param("taskId") Long taskId);
    
    /**
     * 统计任务的失败记录数
     * 
     * @param taskId 任务ID
     * @return 失败记录数
     */
    int countFailedByTaskId(@Param("taskId") Long taskId);
    
    /**
     * 更新迁移记录状态
     * 
     * @param id 记录ID
     * @param status 状态
     * @param errorMsg 错误信息（可选）
     * @return 影响行数
     */
    int updateStatus(@Param("id") Long id, 
                      @Param("status") Integer status,
                      @Param("errorMsg") String errorMsg);
    
    /**
     * 更新迁移记录重试次数
     * 
     * @param id 记录ID
     * @return 影响行数
     */
    int incrementRetryCount(@Param("id") Long id);
    
    /**
     * 根据任务ID删除迁移记录
     * 
     * @param taskId 任务ID
     * @return 影响行数
     */
    int deleteByTaskId(@Param("taskId") Long taskId);
    
    /**
     * 根据任务ID和表名删除迁移记录
     * 
     * @param taskId 任务ID
     * @param tableName 表名
     * @return 影响行数
     */
    int deleteByTaskIdAndTableName(@Param("taskId") Long taskId,
                                    @Param("tableName") String tableName);
}