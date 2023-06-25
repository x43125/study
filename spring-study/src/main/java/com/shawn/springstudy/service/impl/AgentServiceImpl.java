package com.shawn.springstudy.service.impl;

import com.shawn.springstudy.dao.AgentDao;
import com.shawn.springstudy.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangxiang
 * @date 2023/6/25 16:29
 * @description
 */
@Service
public class AgentServiceImpl implements AgentService {

//    @Qualifier("clientDaoImpl")
    @Qualifier("agentDaoImpl")
    @Autowired
//    @Resource(name = "agentDaoImpl")
//    @Resource(name = "clientDaoImpl")

    // @Inject
    // @Named("agentDaoImpl")
    private AgentDao agentDao;

    @Override
    public String getAgentName() {
        return agentDao.getAgentName();
    }
}
