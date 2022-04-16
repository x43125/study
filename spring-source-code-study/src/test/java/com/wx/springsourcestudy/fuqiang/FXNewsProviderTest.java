package com.wx.springsourcestudy.fuqiang;

import com.wx.springsourcestudy.TestCase;
import com.wx.springsourcestudy.fuqiang.simulation.noioc.FXNewsProvider;
import com.wx.springsourcestudy.fuqiang.simulation.noioc.FXNewsProviderInjectWithConstructor;
import com.wx.springsourcestudy.fuqiang.simulation.service.IFXNewsListener;
import com.wx.springsourcestudy.fuqiang.simulation.service.IFXNewsPersister;
import com.wx.springsourcestudy.fuqiang.simulation.service.impl.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * @Author: x43125
 * @Date: 22/04/06
 */
public class FXNewsProviderTest extends TestCase {
    @Test
    public void test01() {
        // 使用了构造方法的调用既可以将耦合提上一层
        FXNewsProviderInjectWithConstructor dowJonesNewsProvider =
                new FXNewsProviderInjectWithConstructor(new DowJonesNewsListener(), new DowJonesNewsPersister());
        FXNewsProviderInjectWithConstructor marketWin24NewsProvider =
                new FXNewsProviderInjectWithConstructor(new MarketWin24NewsListener(), new DowJonesNewsPersister());

        // 绑定依赖关系的绑定代码
        IFXNewsListener newsListener = new DowJonesNewsListener();
        IFXNewsPersister newsPersister = new DowJonesNewsPersister();
        FXNewsProviderInjectWithConstructor newsProvider = new FXNewsProviderInjectWithConstructor(newsListener, newsPersister);
        newsProvider.getAndPersistNews();

        // 直接编码方式注入
        /*
        IoContainer container = ...;
        container.register(FXNewsProvider.class, new FXNewsProvider());
        container.register(IFXNewsListener.class, new DowJonesNewsListener());
        FXNewsProvider newsProvider = (FXNewsProvider) container.get(FXNewsProvider.class);
        newProvider.getAndPersistNews();
        */
        // 接口方式注入
        /*
        IoContainer container = ...;
        container.register(FXNewsProvider.class, new FXNewsProvider());
        container.register(IFXNewsListener.class, new DowJonesNewsListener()); ...
        container.bind(IFXNewsListenerCallable.class, container.get(IFXNewsListener.class)); ...
        FXNewsProvider newsProvider = (FXNewsProvider) container.get(FXNewsProvider.class);
        newProvider.getAndPersistNews();
        */
        // xml方式注入
        /*
        container.readConfigurationFiles(...);
        FXNewsProvider newsProvider = (FXNewsProvider)container.getBean("newsProvider"); newsProvider.getAndPersistNews();
         */
    }

    private FXNewsProviderInjectWithConstructor newsProvider;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // 方便测试，可以通过注入返回为假的依赖对象来控制测试结果
        newsProvider = new FXNewsProviderInjectWithConstructor(new MockNewsListener(), new MockNewsPersister());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        newsProvider = null;
    }

    public void testGetAndPersistNewsWithoutResourceAvailable() {
        try {
            newsProvider.getAndPersistNews();
            fail("Since MockNewsListener has no news support, we should fail to get above.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 模拟main方法
    // 模拟注册、获取bean的过程
    @Test
    public void testMain() {
        // 生成 注册器
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
        BeanFactory container = bind(beanRegistry);
        FXNewsProvider fxNewsProvider = (FXNewsProvider) container.getBean("djNewsProvider");
        fxNewsProvider.getAndPersistNews();
    }

    private BeanFactory bind(BeanDefinitionRegistry registry) {
        // 生成各个bean
        AbstractBeanDefinition newsProvider = new RootBeanDefinition(FXNewsProvider.class);
        AbstractBeanDefinition newsListener = new RootBeanDefinition(DowJonesNewsListener.class);
        AbstractBeanDefinition newsPersister = new RootBeanDefinition(DowJonesNewsPersister.class);
        // 将各个bean转成 beanDefinition，并注册到BeanDefinitionMap中
        registry.registerBeanDefinition("djNewsProvider", newsProvider);
        registry.registerBeanDefinition("djListener", newsListener);
        registry.registerBeanDefinition("djPersister", newsPersister);
        // 指定依赖关系
        // 1. 通过构造方法注入
        ConstructorArgumentValues argValues = new ConstructorArgumentValues();
        argValues.addIndexedArgumentValue(0, newsListener);
        argValues.addIndexedArgumentValue(1, newsPersister);
        newsProvider.setConstructorArgumentValues(argValues);
        // 2. 通过setter方法注入
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("newsListener", newsListener));
        propertyValues.addPropertyValue(new PropertyValue("newsPersister", newsPersister));
        newsProvider.setPropertyValues(propertyValues);

        // 3. 完成绑定
        return (BeanFactory) registry;
    }

}

































