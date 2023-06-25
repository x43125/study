package com.shawn.springstudy.dao.impl;

import com.shawn.springstudy.dao.AgentDao;
import org.springframework.stereotype.Repository;

/**
 * @author wangxiang
 * @date 2023/6/25 16:28
 * @description
 */
@Repository
public class AgentDaoImpl implements AgentDao {
    @Override
    public String getAgentName() {
        return "hello world";
    }
}
