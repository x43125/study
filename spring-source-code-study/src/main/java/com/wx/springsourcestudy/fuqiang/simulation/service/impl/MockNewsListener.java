package com.wx.springsourcestudy.fuqiang.simulation.service.impl;

import com.wx.springsourcestudy.fuqiang.simulation.pojo.FXNewsBean;
import com.wx.springsourcestudy.fuqiang.simulation.service.IFXNewsListener;

/**
 * @Author: x43125
 * @Date: 22/04/06
 */
public class MockNewsListener implements IFXNewsListener {
    @Override
    public String[] getAvailableNewsIds() {
        throw new RuntimeException();
    }

    @Override
    public FXNewsBean getNewsByPK(String newsId) {
        return null;
    }

    @Override
    public void postProcessIfNecessary(String newsId) {

    }
}
