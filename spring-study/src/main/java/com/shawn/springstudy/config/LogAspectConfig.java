//package com.shawn.springstudy.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.ImportResource;
//
///**
// * @author wangxiang
// * @date 2023/6/24 15:20
// * @description
// */
//@Configuration
//@ImportResource("classpath:com/shawn/aop/*.xml")
//public class LogAspectConfig {
//
//}

// 使用配置类之后就不能使用ClassPathXmlApplicationContext来加载context了，否则会double，得再研究研究