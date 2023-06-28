package com.shawn.springstudylifecycle01;

import com.shawn.springstudylifecycle01.model.Bean1;
import com.shawn.springstudylifecycle01.model.Bean2;
import com.shawn.springstudylifecycle01.model.Bean3;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangxiang
 * @date 2023/6/28 22:06
 * @description
 */
public class DigInAutowired {
    public static void main(String[] args) throws Throwable {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerSingleton("bean2", new Bean2());
        beanFactory.registerSingleton("bean3", new Bean3());
        // 处理@Value的注解
        beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        // 处理${}信息
        beanFactory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);

        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setBeanFactory(beanFactory);

        Bean1 bean1 = new Bean1();
//        System.out.println(bean1);
//      // 1. 直接注入
//        processor.postProcessProperties(null, bean1, "bean1");
//        System.out.println(bean1);
        // 2. 调用InjectionMetadata 来进行依赖注入，注入时按类型查找值
//        Method method = AutowiredAnnotationBeanPostProcessor.class.getDeclaredMethod("findAutowiringMetadata", String.class, Class.class, PropertyValues.class);
//        method.setAccessible(true);
//        // 获取Bean1上加了 @Autowired @Value的成员变量，方法等信息
//        InjectionMetadata metadata = (InjectionMetadata) method.invoke(processor, "bean1", Bean1.class, null);
//        System.out.println(metadata);
        // 调用 InjectionMetadata 来进行依赖注入，注入时按照类型查找值
//        metadata.inject(bean1, "bea n1", null);
//        System.out.println(bean1);
        // 3. 按类型查找值
        Field bean3 = Bean1.class.getDeclaredField("bean3");
        DependencyDescriptor dd1 = new DependencyDescriptor(bean3, false);
        Object o = beanFactory.resolveDependency(dd1, null, null, null);
        System.out.println(o);

        Method setBean3 = Bean1.class.getDeclaredMethod("setBean3", Bean3.class);
        DependencyDescriptor dd2 = new DependencyDescriptor(new MethodParameter(setBean3, 0), true);
        Object o1 = beanFactory.resolveDependency(dd2, null, null, null);
        System.out.println(o1);

        Method setValue = Bean1.class.getDeclaredMethod("setValue", String.class);
        DependencyDescriptor dd3 = new DependencyDescriptor(new MethodParameter(setValue, 0), true);
        Object o2 = beanFactory.resolveDependency(dd3, null, null, null);
        System.out.println(o2);


    }
}
