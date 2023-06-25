package com.shawn.springstudy.service.impl;

import com.shawn.springstudy.dao.AgentDao;
import com.shawn.springstudy.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author wangxiang
 * @date 2023/6/25 16:29
 * @description
 */
@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
//    @Qualifier("agentDaoImpl")
    @Qualifier("clientDaoImpl")
    private AgentDao agentDao;

    @Override
    public String getAgentName() {
        return agentDao.getAgentName();
    }
}
