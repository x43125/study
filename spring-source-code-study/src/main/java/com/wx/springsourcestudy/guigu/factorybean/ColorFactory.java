package com.wx.springsourcestudy.guigu.factorybean;

import com.wx.springsourcestudy.guigu.bean.Color;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
public class ColorFactory implements FactoryBean<Color> {

    @Override
    public Color getObject() throws Exception {
        System.out.println("------------------");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
