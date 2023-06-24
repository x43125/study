package com.shawn.springstudy;


import com.shawn.springstudy.service.AopDemoServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangxiang
 * @date 2023/6/21 20:42
 * @description
 */
public class LogAspectTest {
    public static void main(String[] args) {
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
