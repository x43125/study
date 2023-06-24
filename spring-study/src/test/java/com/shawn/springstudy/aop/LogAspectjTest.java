package com.shawn.springstudy.aop;

import com.shawn.springstudy.service.IJdkProxyService;
import com.shawn.springstudy.service.impl.AopDemoServiceImpl;
import com.shawn.springstudy.service.impl.AopDemoServiceImpl2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * @author wangxiang
 * @date 2023/6/24 15:29
 * @description
 */
@SpringBootTest
public class LogAspectjTest {

    @Autowired
    private IJdkProxyService iJdkProxyService;

    @Test
    void LogAspectJTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("com/shawn/aop/LogAspectj.xml");
        AopDemoServiceImpl service = context.getBean("demoService", AopDemoServiceImpl.class);
        AopDemoServiceImpl2 service2 = context.getBean("demoService2", AopDemoServiceImpl2.class);

        service.doMethod1();
        service.doMethod2();
        try {
            service.doMethod3();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }

        System.out.println("======================================================================");
        service2.doMethod1();
        service2.doMethod2();
        try {
            service2.doMethod3();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }


    @Test
    void annotationLogAspectJTest() {
        iJdkProxyService.doMethod1();
        iJdkProxyService.doMethod2();
        try {
            iJdkProxyService.doMethod3();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }
}
