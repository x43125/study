package com.wx.springsourcestudy.fuqiang.simulation.noioc;

import com.wx.springsourcestudy.fuqiang.simulation.pojo.FXNewsBean;
import com.wx.springsourcestudy.fuqiang.simulation.service.IFXNewsListener;
import com.wx.springsourcestudy.fuqiang.simulation.service.IFXNewsPersister;
import com.wx.springsourcestudy.fuqiang.simulation.service.impl.DowJonesNewsListener;
import com.wx.springsourcestudy.fuqiang.simulation.service.impl.DowJonesNewsPersister;

/**
 * @Author: x43125
 * @Date: 22/04/06
 * @Description: 使用构造方式注入的Bean：声明一个类似下文的构造方法，让外界（IOC容器）知道这个Bean依赖哪些Bean
 */
public class FXNewsProviderInjectWithConstructor {
    private IFXNewsListener newsListener;
    private IFXNewsPersister newPersistener;

    public FXNewsProviderInjectWithConstructor(IFXNewsListener newsListener, IFXNewsPersister newPersistener) {
        // 1. 声明如此的构造方法即可使用构造器注入方式注入
        this.newsListener = newsListener;
        this.newPersistener = newPersistener;
    }

    public void getAndPersistNews() {
        // 2. 然后调用他的方法
        String[] newsIds = newsListener.getAvailableNewsIds();
        if (newsIds == null || newsIds.length == 0) {
            return;
        }
        for (String newsId : newsIds) {
            FXNewsBean newsBean = newsListener.getNewsByPK(newsId);
            newPersistener.persistNews(newsBean);
            newsListener.postProcessIfNecessary(newsId);
        }
    }
}
