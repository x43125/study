package com.shawn.springstudy.aop;

import com.shawn.springstudy.service.IJdkProxyService;
import com.shawn.springstudy.service.impl.AopDemoServiceImpl;
import com.shawn.springstudy.service.impl.AopDemoServiceImpl2;
import com.shawn.springstudy.service.impl.AopDemoServiceImpl3;
import com.shawn.springstudy.service.impl.CglibProxyDemoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IJdkProxyService iJdkProxyService;
    @Autowired
    private CglibProxyDemoServiceImpl cglibProxyDemoService;
    @Autowired
    private AopDemoServiceImpl3 aopDemoServiceImpl3;

    @Test
    void xmlLogAspectJTest() {
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
    void annotationJdkLogAspectJTest() {
        iJdkProxyService.doMethod1();
        iJdkProxyService.doMethod2();
        try {
            iJdkProxyService.doMethod3();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }

    @Test
    void annotationCglibLogAspectJTest() {
        cglibProxyDemoService.doMethod1();
        cglibProxyDemoService.doMethod2();
        try {
            cglibProxyDemoService.doMethod3();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }

    @Test
    void logAspectJTest() {
        aopDemoServiceImpl3.doMethod1();
        aopDemoServiceImpl3.doMethod2();
        try {
            aopDemoServiceImpl3.doMethod3();
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }
}
