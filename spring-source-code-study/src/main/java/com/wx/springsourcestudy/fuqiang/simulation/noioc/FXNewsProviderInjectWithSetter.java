package com.wx.springsourcestudy.fuqiang.simulation.noioc;

import com.wx.springsourcestudy.fuqiang.simulation.pojo.FXNewsBean;
import com.wx.springsourcestudy.fuqiang.simulation.service.IFXNewsListener;
import com.wx.springsourcestudy.fuqiang.simulation.service.IFXNewsPersister;

/**
 * @Author: x43125
 * @Date: 22/04/06
 * @Description: 使用Setter方式注入的Bean：为需要注入的依赖声明类似下文的setter方法，让外界（IOC容器）知道这个Bean依赖哪些Bean
 */
public class FXNewsProviderInjectWithSetter {
    private IFXNewsListener newsListener;
    private IFXNewsPersister newPersistener;

    // 1. 声明如此的set方法，即可使用setter注入方式注入
    public void setNewsListener(IFXNewsListener newsListener) {
        this.newsListener = newsListener;
    }
    public void setNewPersistener(IFXNewsPersister newPersistener) {
        this.newPersistener = newPersistener;
    }

    public IFXNewsListener getNewsListener() {
        return newsListener;
    }

    public IFXNewsPersister getNewPersistener() {
        return newPersistener;
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
