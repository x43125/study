package com.shawn.springstudy.ioc;

import com.shawn.springstudy.context.UserRegisteredEvent;
import com.shawn.springstudy.dao.UserDao;
import com.shawn.springstudy.factory.Bean1;
import com.shawn.springstudy.factory.BeanFactoryConfig;
import com.shawn.springstudy.model.Student;
import com.shawn.springstudy.model.User;
import com.shawn.springstudy.ref.MyBeanFactoryPostProcessor;
import com.shawn.springstudy.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author wangxiang
 * @date 2023/6/28 10:29
 * @description
 */
@Slf4j
//@SpringBootTest
public class RefreshTest {

    @Test
    void testRefresh() {
        System.out.println("Init application context");
        // create and configure beans
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "com.shawn.springstudy");
        // retrieve configured instance
        User user = (User) context.getBean("user");
        // print info from beans
        System.out.println("==========|||||||||================ " + user);

        Student student = (Student) context.getBean("student");
        System.out.println("==========|||||||||================ " + student);

        System.out.println("Shutdown application context");
        context.registerShutdownHook();
    }

    @Test
    void testDefaultListableBeanFactory() throws NoSuchFieldException, IllegalAccessException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "com.shawn.springstudy");

        // beanFactory
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
        map.forEach((k, v) -> System.out.println(k + "\t==== " + v));

        // context
        // 1. resource
        Resource resource = context.getResource("classpath*:META-INF/spring.factories");
        System.out.println("resource = " + resource);

        // 2. environment
        ConfigurableEnvironment environment = context.getEnvironment();
        System.out.println("environment = " + environment);
        String property = environment.getProperty("java.library.path");
        System.out.println("property = " + property);

        // 3. event
        context.publishEvent(new UserRegisteredEvent(context));

    }

    @Test
    void testStudyBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(BeanFactoryConfig.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("config", beanDefinition);
//        AbstractBeanDefinition beanDefinition1 = BeanDefinitionBuilder.genericBeanDefinition(BeansConfig.class).getBeanDefinition();
//        beanFactory.registerBeanDefinition("config1", beanDefinition1);

        // 初始的时候只有config一个bean
        printBeanDefinitionNames(beanFactory);

        // 这一步把注解相关的后处理器加到容器中
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        // 所以此时容器中多了与注解处理相关的后处理器bean
        printBeanDefinitionNames(beanFactory);

        // 将自建的beanFactoryPostProcessor添加进容器中
        AbstractBeanDefinition beanDefinition2 = BeanDefinitionBuilder.genericBeanDefinition(MyBeanFactoryPostProcessor.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("myBeanFactoryPostProcessor", beanDefinition2);

        // 让后处理器运行：从beanFactory中获取刚刚加载进去的后处理器bean，可以通过 type来获取
        // 获取到之后，再for循环执行每个后处理器，这些后处理器做了一系列重要的操作
        // 比如：internalConfigurationAnnotationProcessor这个后处理器会去寻找所有的被@Configuration注解的类，然后将其内部的bean加载到容器中
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values()
                .forEach(processor -> processor.postProcessBeanFactory(beanFactory));
        // 所以此时再打印，就又多了 BeanFactoryConfig下的两个bean：Bean1, Bean2
        printBeanDefinitionNames(beanFactory);

        // 前面已经将beanPostProcessor注册进容器中了，现在将beanFactory与这些beanPostProcessor关联上 (beanFactory.addBeanPostProcessor)
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanFactory::addBeanPostProcessor);
        printBeanDefinitionNames(beanFactory);
        // 本身是懒汉式创建，调用下面这个方法可以改为 饿汉式：提前创建好对象
        beanFactory.preInstantiateSingletons();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Bean1 bean1 = beanFactory.getBean(Bean1.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean1.getBean2() = " + bean1.getBean2());
    }

    private void printBeanDefinitionNames(DefaultListableBeanFactory beanFactory) {
        System.out.println("=========================== start");
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("=========================== end");
    }

    @Test
    void testStudyApplicationContext() {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/shawn/ioc/UserService.xml");
//        printBeanDefinitionNames(context);
//        UserDao userDao = context.getBean(UserServiceImpl.class).getUserDao();
//        System.out.println("userDao = " + userDao);


//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        printBeanDefinitionNames(beanFactory);
//
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        reader.loadBeanDefinitions("com/shawn/ioc/UserService.xml");
//        printBeanDefinitionNames(beanFactory);

//        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

    }

    private void printBeanDefinitionNames(ApplicationContext context) {
        System.out.println("=========================== start");
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("=========================== end");
    }
}
