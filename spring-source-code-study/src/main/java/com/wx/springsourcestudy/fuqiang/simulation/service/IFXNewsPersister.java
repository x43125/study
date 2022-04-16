package com.wx.springsourcestudy.fuqiang.simulation.service;

import com.wx.springsourcestudy.fuqiang.simulation.pojo.FXNewsBean;

/**
 * @Author: x43125
 * @Date: 22/04/06
 */
public interface IFXNewsPersister {
    void persistNews(FXNewsBean newsBean);
}
