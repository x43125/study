package com.shawn.springstudylifecycle01;

import java.util.ArrayList;
import java.util.List;

/**
 * 模版模式：绝b
 * @author wangxiang
 * @date 2023/6/28 21:09
 * @description
 */
public class StudyMethodTemplate {
    public static void main(String[] args) {
        MyBeanFactory beanFactory = new MyBeanFactory();
        beanFactory.addBeanPostProcessor(bean -> System.out.println("解析 @Autowired"));
        beanFactory.addBeanPostProcessor(bean -> System.out.println("解析 @Resource"));
        Object bean = beanFactory.getBean();
    }

    static class MyBeanFactory {
        public Object getBean() {
            Object bean = new Object();
            System.out.println("构造： " + bean);
            System.out.println("依赖注入： " + bean);
            for (MyBeanPostProcessor processor : processors) {
                processor.inject(bean);
            }
            System.out.println("初始化： " + bean);
            return bean;
        }

        private List<MyBeanPostProcessor> processors = new ArrayList<>();

        public void addBeanPostProcessor(MyBeanPostProcessor processor) {
            processors.add(processor);
        }
    }


    interface MyBeanPostProcessor {
        // 对依赖注入阶段的扩展
        void inject(Object bean);
    }
}

