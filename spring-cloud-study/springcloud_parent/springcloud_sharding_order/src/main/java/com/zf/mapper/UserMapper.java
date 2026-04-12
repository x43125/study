package com.zf.mapper;

import com.zf.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户Mapper
 */
@Mapper
public interface UserMapper {

    /**
     * 插入用户
     */
    int insert(User user);

    /**
     * 根据ID查询用户
     */
    User selectById(@Param("id") Long id);

    /**
     * 查询所有用户
     */
    User selectAll();

    /**
     * 统计用户数量
     */
    long selectCount();

    /**
     * 根据ID更新用户
     */
    int updateById(User user);
}
