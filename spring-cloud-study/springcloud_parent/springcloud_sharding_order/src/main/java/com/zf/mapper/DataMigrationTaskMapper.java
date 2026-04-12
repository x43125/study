package com.zf.mapper;

import com.zf.entity.DataMigrationTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据迁移任务Mapper接口
 * 
 * @author zf
 * @since 2026-04-12
 */
@Mapper
public interface DataMigrationTaskMapper {
    
    /**
     * 插入迁移任务
     * 
     * @param task 迁移任务
     * @return 影响行数
     */
    int insert(DataMigrationTask task);
    
    /**
     * 根据ID查询迁移任务
     * 
     * @param id 任务ID
     * @return 迁移任务
     */
    DataMigrationTask selectById(@Param("id") Long id);
    
    /**
     * 根据任务名称查询迁移任务
     * 
     * @param taskName 任务名称
     * @return 迁移任务
     */
    DataMigrationTask selectByTaskName(@Param("taskName") String taskName);
    
    /**
     * 更新迁移任务状态
     * 
     * @param id 任务ID
     * @param status 状态
     * @return 影响行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    /**
     * 更新迁移进度
     * 
     * @param id 任务ID
     * @param migratedCount 已迁移数量
     * @param totalCount 总数量
     * @return 影响行数
     */
    int updateProgress(@Param("id") Long id, 
                       @Param("migratedCount") Long migratedCount,
                       @Param("totalCount") Long totalCount);
    
    /**
     * 更新任务失败信息
     * 
     * @param id 任务ID
     * @param errorMsg 错误信息
     * @return 影响行数
     */
    int updateErrorInfo(@Param("id") Long id, @Param("errorMsg") String errorMsg);
    
    /**
     * 设置任务开始时间
     * 
     * @param id 任务ID
     * @return 影响行数
     */
    int setStartTime(@Param("id") Long id);
    
    /**
     * 设置任务结束时间
     * 
     * @param id 任务ID
     * @return 影响行数
     */
    int setEndTime(@Param("id") Long id);
    
    /**
     * 查询所有迁移任务
     * 
     * @return 迁移任务列表
     */
    List<DataMigrationTask> selectAll();
    
    /**
     * 根据状态查询迁移任务
     * 
     * @param status 状态
     * @return 迁移任务列表
     */
    List<DataMigrationTask> selectByStatus(@Param("status") Integer status);
    
    /**
     * 删除迁移任务
     * 
     * @param id 任务ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Long id);
}