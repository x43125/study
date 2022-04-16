package com.wx.springsourcestudy.guigu.applicationcontext.mapperscan.dao;

import org.springframework.stereotype.Repository;

/**
 * @Author: x43125
 * @Date: 22/03/27
 */
@Repository
public class UserDao {
    private int label = 1;

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "UserDao{" +
                "label=" + label +
                '}';
    }
}
