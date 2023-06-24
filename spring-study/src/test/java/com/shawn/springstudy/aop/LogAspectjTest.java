package com.shawn.springstudy.aop;

import com.shawn.springstudy.service.AopDemoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangxiang
 * @date 2023/6/24 15:29
 * @description
 */
@SpringBootTest
public class LogAspectjTest {
    @Test
    void LogAspectJTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("LogAspectj.xml");
        AopDemoServiceImpl service = context.getBean("demoService", AopDemoServiceImpl.class);

        service.doMethod1();
        service.doMethod2();
        try {
            service.doMethod3();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }
}
