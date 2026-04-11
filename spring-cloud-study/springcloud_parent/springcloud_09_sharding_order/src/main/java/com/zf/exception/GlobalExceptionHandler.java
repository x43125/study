package com.zf.exception;

import com.zf.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseDTO<Void> handleException(Exception e) {
        log.error("系统异常：{}", e.getMessage(), e);
        return ResponseDTO.error("系统异常：" + e.getMessage());
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseDTO<Void> handleRuntimeException(RuntimeException e) {
        log.error("运行时异常：{}", e.getMessage(), e);
        return ResponseDTO.error("运行时异常：" + e.getMessage());
    }

    /**
     * 处理非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDTO<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("参数异常：{}", e.getMessage());
        return ResponseDTO.error("参数异常：" + e.getMessage());
    }
}