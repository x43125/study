package com.wx.base;

import com.wx.base.service.InterfaceStudy;
import com.wx.base.service.impl.InterfaceStudyImpl;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangxiang
 * @date 2023/7/2 11:36
 * @description
 */
public class InterfaceTest {
    @Test
    public void test01() {
        InterfaceStudy interfaceStudy = new InterfaceStudyImpl("zhangsan", 1);
        InterfaceStudy interfaceStudy2 = new InterfaceStudyImpl("zhangsan", 2);
        interfaceStudy.func1();
        interfaceStudy.func2();
        System.out.println("interfaceStudy.x = " + interfaceStudy.x);

        System.out.println(interfaceStudy2.equals(interfaceStudy));
        Set<InterfaceStudy> set = new HashSet<>();
        set.add(interfaceStudy);
        set.add(interfaceStudy2);
        System.out.println(set.size());
    }
}
