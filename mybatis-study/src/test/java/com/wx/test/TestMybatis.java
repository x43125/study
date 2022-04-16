package com.wx.test;

import com.wx.dao.UserDao;
import com.wx.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/05/16
 */
public class TestMybatis {

    @Test
    public void testAdd() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession);

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User(
                3,
                "小李",
                11,
                new Date()
        );

        try {
            int count = userDao.saveUser(user);
            System.out.println(count);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdate() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession);

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(3);
        user.setName("小李");
        user.setAge(22);
        try {
            int count = userDao.update(user);
            System.out.println(count);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testQueryByPage() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession);

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        try {
            List<User> userList = userDao.queryByPage(1, 2);
            userList.forEach(user -> System.out.println(user));
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
