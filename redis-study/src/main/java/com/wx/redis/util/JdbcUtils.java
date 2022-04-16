package com.wx.redis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/09/05
 */
public class JdbcUtils {

    public static Connection getConnection(String username, String password) {
        String url = "jdbc:mysql://47.98.59.193:3306/study";
        String driver = "com.mysql.jdbc.Driver";
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
