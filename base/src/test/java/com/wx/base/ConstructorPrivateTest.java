package com.wx.base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 22/05/28
 */
public class ConstructorPrivateTest {
    @Test
    public void testConstruct() {
//        ConstructorPrivate c = new ConstructorPrivate("sss", 111);
        ConstructorPrivate c = ConstructorPrivate.getInstance();
        c.setName("zhangsan");
        System.out.println("c.getName() = " + c.getName());


    }

}
