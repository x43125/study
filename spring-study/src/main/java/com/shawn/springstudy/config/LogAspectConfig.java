package com.shawn.springstudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author wangxiang
 * @date 2023/6/24 15:20
 * @description
 */
@Configuration
@ImportResource("classpath:*.xml")
public class LogAspectConfig {

}
