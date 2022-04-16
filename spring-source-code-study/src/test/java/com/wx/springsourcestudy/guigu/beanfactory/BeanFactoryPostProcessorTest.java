package com.wx.springsourcestudy.guigu.beanfactory;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: x43125
 * @Date: 22/03/28
 */
public class BeanFactoryPostProcessorTest {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ExtConfig1.class);

        context.close();
    }
}
