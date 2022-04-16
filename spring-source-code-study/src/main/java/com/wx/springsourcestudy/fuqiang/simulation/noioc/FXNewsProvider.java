package com.wx.springsourcestudy.fuqiang.simulation.noioc;

import com.wx.springsourcestudy.fuqiang.simulation.pojo.FXNewsBean;
import com.wx.springsourcestudy.fuqiang.simulation.service.IFXNewsListener;
import com.wx.springsourcestudy.fuqiang.simulation.service.IFXNewsPersister;
import com.wx.springsourcestudy.fuqiang.simulation.service.impl.DowJonesNewsListener;
import com.wx.springsourcestudy.fuqiang.simulation.service.impl.DowJonesNewsPersister;

/**
 * @Author: x43125
 * @Date: 22/04/06
 */
public class FXNewsProvider {
    private IFXNewsListener newsListener;
    private IFXNewsPersister newPersistener;

    public FXNewsProvider() {
        // 1. 通常的做事方式，需要什么就自己new
        newsListener = new DowJonesNewsListener();
        newPersistener = new DowJonesNewsPersister();
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
