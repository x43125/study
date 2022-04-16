package com.wx.redis;

import com.wx.redis.entity.Price;
import com.wx.redis.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Descrption:
 * @Author: x43125
 * @Date: 21/09/05
 */
public class PriceDao {

    static Connection connection;

    public PriceDao(String username, String password) {
        connection = JdbcUtils.getConnection(username, password);
    }

    public Price selectByPrimaryKey(int id) {
        String querySql = "select * from price where id=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Price price = new Price();
        try {
            preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    price.setId(resultSet.getLong("id"));
                    price.setTotal(resultSet.getBigDecimal("total"));
                    price.setFront(resultSet.getBigDecimal("front"));
                    price.setEnd(resultSet.getBigDecimal("end"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return price;
    }

    public void updateByPrimaryKey(Price price) {
        String updateSql = "update price set id=?,total=?,front=?,end=? where id=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setLong(1, price.getId());
            preparedStatement.setBigDecimal(2, price.getTotal());
            preparedStatement.setBigDecimal(3, price.getFront());
            preparedStatement.setBigDecimal(4, price.getEnd());
            preparedStatement.setLong(5, price.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertSelective(Price price) {

        String insertSql;
        if (price.getId() != null) {
            insertSql = "insert into price(id,total,front,end) values(?,?,?,?)";
        } else {
            insertSql = "insert into price(total,front,end) values(?,?,?)";
        }

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insertSql);
            if (price.getId() != null) {
                preparedStatement.setLong(1, price.getId());
                preparedStatement.setBigDecimal(2, price.getTotal());
                preparedStatement.setBigDecimal(3, price.getFront());
                preparedStatement.setBigDecimal(4, price.getEnd());
            } else {
                preparedStatement.setBigDecimal(1, price.getTotal());
                preparedStatement.setBigDecimal(2, price.getFront());
                preparedStatement.setBigDecimal(3, price.getEnd());
            }
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
