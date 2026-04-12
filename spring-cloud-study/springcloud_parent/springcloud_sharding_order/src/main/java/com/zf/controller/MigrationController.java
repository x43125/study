package com.zf.controller;

import com.zf.dto.MigrationTaskDTO;
import com.zf.dto.ResponseDTO;
import com.zf.service.DataMigrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据迁移管理接口
 * 
 * 功能：
 * 1. 创建和管理迁移任务
 * 2. 执行迁移操作
 * 3. 查询任务状态和进度
 * 4. 数据校验
 * 5. 回滚操作
 * 
 * @author zf
 * @since 2026-04-12
 */
@Slf4j
@RestController
@RequestMapping("/api/migration")
public class MigrationController {

    @Autowired
    private DataMigrationService migrationService;

    /**
     * 创建迁移任务
     * 
     * @param taskName   任务名称
     * @param tableName  表名（t_order或t_order_item）
     * @param startMonth 起始月份（202510）
     * @param endMonth   结束月份（202610）
     * @return 任务ID
     */
    @PostMapping("/create")
    public ResponseDTO<Long> createTask(
            @RequestParam String taskName,
            @RequestParam String tableName,
            @RequestParam String startMonth,
            @RequestParam String endMonth) {

        log.info("创建迁移任务 - taskName: {}, tableName: {}, startMonth: {}, endMonth: {}",
                taskName, tableName, startMonth, endMonth);

        try {
            Long taskId = migrationService.createTask(taskName, tableName, startMonth, endMonth);
            return ResponseDTO.success(taskId);
        } catch (Exception e) {
            log.error("创建迁移任务失败", e);
            return ResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 执行迁移任务（同步）
     * 
     * @param taskId 任务ID
     * @return 执行结果
     */
    @PostMapping("/execute/{taskId}")
    public ResponseDTO<String> executeMigration(@PathVariable Long taskId) {
        log.info("执行迁移任务 - taskId: {}", taskId);

        try {
            migrationService.executeMigration(taskId);
            return ResponseDTO.success("迁移任务已启动");
        } catch (Exception e) {
            log.error("执行迁移任务失败 - taskId: {}", taskId, e);
            return ResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 异步执行迁移任务
     * 
     * @param taskId 任务ID
     * @return 执行结果
     */
    @PostMapping("/execute-async/{taskId}")
    public ResponseDTO<String> executeMigrationAsync(@PathVariable Long taskId) {
        log.info("异步执行迁移任务 - taskId: {}", taskId);

        try {
            migrationService.executeMigrationAsync(taskId);
            return ResponseDTO.success("迁移任务已异步启动");
        } catch (Exception e) {
            log.error("异步执行迁移任务失败 - taskId: {}", taskId, e);
            return ResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 查询任务信息
     * 
     * @param taskId 任务ID
     * @return 任务信息
     */
    @GetMapping("/task/{taskId}")
    public ResponseDTO<MigrationTaskDTO> getTaskInfo(@PathVariable Long taskId) {
        log.debug("查询任务信息 - taskId: {}", taskId);

        try {
            MigrationTaskDTO taskInfo = migrationService.getTaskInfo(taskId);
            if (taskInfo == null) {
                return ResponseDTO.error("任务不存在");
            }
            return ResponseDTO.success(taskInfo);
        } catch (Exception e) {
            log.error("查询任务信息失败 - taskId: {}", taskId, e);
            return ResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 查询所有任务
     * 
     * @return 任务列表
     */
    @GetMapping("/tasks")
    public ResponseDTO<List<MigrationTaskDTO>> getAllTasks() {
        log.debug("查询所有任务");

        try {
            List<MigrationTaskDTO> tasks = migrationService.getAllTasks();
            return ResponseDTO.success(tasks);
        } catch (Exception e) {
            log.error("查询所有任务失败", e);
            return ResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 查询指定状态的任务
     * 
     * @param status 状态（0-待执行 1-执行中 2-成功 3-失败）
     * @return 任务列表
     */
    @GetMapping("/tasks/status/{status}")
    public ResponseDTO<List<MigrationTaskDTO>> getTasksByStatus(@PathVariable Integer status) {
        log.debug("查询指定状态的任务 - status: {}", status);

        try {
            List<MigrationTaskDTO> tasks = migrationService.getTasksByStatus(status);
            return ResponseDTO.success(tasks);
        } catch (Exception e) {
            log.error("查询指定状态的任务失败 - status: {}", status, e);
            return ResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 数据校验
     * 
     * @param taskId 任务ID
     * @return 校验结果
     */
    @PostMapping("/validate/{taskId}")
    public ResponseDTO<Boolean> validateData(@PathVariable Long taskId) {
        log.info("数据校验 - taskId: {}", taskId);

        try {
            boolean result = migrationService.validateData(taskId);
            return ResponseDTO.success(result);
        } catch (Exception e) {
            log.error("数据校验失败 - taskId: {}", taskId, e);
            return ResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 回滚迁移
     * 
     * @param taskId 任务ID
     * @return 回滚结果
     */
    @PostMapping("/rollback/{taskId}")
    public ResponseDTO<String> rollbackMigration(@PathVariable Long taskId) {
        log.info("回滚迁移 - taskId: {}", taskId);

        try {
            migrationService.rollbackMigration(taskId);
            return ResponseDTO.success("回滚成功");
        } catch (Exception e) {
            log.error("回滚迁移失败 - taskId: {}", taskId, e);
            return ResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 暂停任务
     * 
     * @param taskId 任务ID
     * @return 暂停结果
     */
    @PostMapping("/pause/{taskId}")
    public ResponseDTO<String> pauseTask(@PathVariable Long taskId) {
        log.info("暂停任务 - taskId: {}", taskId);

        try {
            migrationService.pauseTask(taskId);
            return ResponseDTO.success("任务已暂停");
        } catch (Exception e) {
            log.error("暂停任务失败 - taskId: {}", taskId, e);
            return ResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 继续任务
     * 
     * @param taskId 任务ID
     * @return 继续结果
     */
    @PostMapping("/resume/{taskId}")
    public ResponseDTO<String> resumeTask(@PathVariable Long taskId) {
        log.info("继续任务 - taskId: {}", taskId);

        try {
            migrationService.resumeTask(taskId);
            return ResponseDTO.success("任务已继续");
        } catch (Exception e) {
            log.error("继续任务失败 - taskId: {}", taskId, e);
            return ResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 取消任务
     * 
     * @param taskId 任务ID
     * @return 取消结果
     */
    @PostMapping("/cancel/{taskId}")
    public ResponseDTO<String> cancelTask(@PathVariable Long taskId) {
        log.info("取消任务 - taskId: {}", taskId);

        try {
            migrationService.cancelTask(taskId);
            return ResponseDTO.success("任务已取消");
        } catch (Exception e) {
            log.error("取消任务失败 - taskId: {}", taskId, e);
            return ResponseDTO.error(e.getMessage());
        }
    }

    /**
     * 删除任务
     * 
     * @param taskId 任务ID
     * @return 删除结果
     */
    @DeleteMapping("/task/{taskId}")
    public ResponseDTO<String> deleteTask(@PathVariable Long taskId) {
        log.info("删除任务 - taskId: {}", taskId);

        try {
            migrationService.deleteTask(taskId);
            return ResponseDTO.success("任务已删除");
        } catch (Exception e) {
            log.error("删除任务失败 - taskId: {}", taskId, e);
            return ResponseDTO.error(e.getMessage());
        }
    }
}