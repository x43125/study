package com.wx.base.service.impl;

import com.wx.base.service.InterfaceStudy;

/**
 * @author wangxiang
 * @date 2023/7/2 11:35
 * @description
 */
public class InterfaceStudyImpl implements InterfaceStudy {

    private String name;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public InterfaceStudyImpl(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        InterfaceStudyImpl o = (InterfaceStudyImpl) obj;
        return this.getName().equals(o.getName());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + name.hashCode();

        return result;
    }

    @Override
    public void func2() {
        System.out.println("this is func2, it implemented by its son, soutv: y " + y);
    }

    public void func3() {
        System.out.println("this is func3");
    }

    public void func3(int i) {
        System.out.println("this is func3 too");
    }

    public void func3(int x, int y) {
        System.out.println("this is func3 too,too");
    }

    public void func3(int y, String x) {
        System.out.println("this is func3 too, too, too");
    }

    public void func3(String x, int y) {
        System.out.println("this is func3 too, too, too, too");
    }


}
