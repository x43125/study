package com.wx.dao;

import com.wx.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/05/16
 */
public interface UserDao {

    /**
     * 保存用户
     *
     * @param user
     */
    int saveUser(User user);

    /**
     * 更新数据
     *
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 分页查询
     *
     * @param start
     * @param rows
     * @return
     */
    List<User> queryByPage(@Param("start") Integer start, @Param("rows") Integer rows);
}
