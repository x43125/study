package com.wx.springsourcestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * @author x43125
 */
@SpringBootApplication
public class SpringSourceStudyApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringSourceStudyApplication.class, args);
        System.out.println("==============================================");
//        HelloWorld helloWorld =(HelloWorld) context.getBean("helloWorld");
//        helloWorld.sout();
//        MainConfig3 bean = context.getBean(MainConfig3.class);
//        HelloWorld helloWorld = bean.getHelloWorld();
//        helloWorld.sout();

//
//        // ApplicationContext study
//        // ApplicationContext has four main function:
//        ConfigurableApplicationContext context = SpringApplication.run(SpringSourceStudyApplication.class, args);
//        // 1. spring.messages 国际化
//        System.out.println("1. spring.messages: ");
//        System.out.println("你好的三国语言写法：");
//        System.out.println(context.getMessage("hi", null, Locale.CHINA));
//        System.out.println(context.getMessage("hi", null, Locale.ENGLISH));
//        System.out.println(context.getMessage("hi", null, Locale.JAPAN));
//        // 2. spring.resources 根据通配符获取一组资源
//        System.out.println("2. spring.resources: ");
//        Resource[] resources = context.getResources("cla" +
//                "sspath:META-INF/spring.factories");
//        for (Resource resource : resources) {
//            System.out.println(resource);
//        }
//        // 3. spring.getEnv 获取环境变量，配置文件中内容
//        System.out.println("3. spring.getEnv: ");
//        System.out.println(context.getEnvironment().getProperty("java_home"));
//        System.out.println(context.getEnvironment().getProperty("server.port"));
//        System.out.println(context.getEnvironment().getProperty("python_home"));
//        // 4. spring.publish 订阅 发送事件：解耦
//        System.out.println("4. spring.publish: ");
////        context.publishEvent(new UserRegisteredEvent(context));
//        context.getBean(Component1.class).register();
    }
}
