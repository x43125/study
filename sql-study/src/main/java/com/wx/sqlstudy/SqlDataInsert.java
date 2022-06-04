package com.wx.sqlstudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * cha数据库
 */
class SqlDataInsert {

    public static void main(String[] args) {
        insert(10 * 10000);
    }

    private static void insert(int number) {
        Connection con = null;
        PreparedStatement pst = null;
//        String url = "jdbc:postgresql://192.168.11.128:5432/postgres";
//        String user = "postgres";
//        String password = "123ABCdef*";
//        String driver = "org.postgresql.Driver";

        String url = "jdbc:mysql://47.98.59.193:3306/test?serverTimezone=UTC";
        String user = "root";
        String password = "root";
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            StringBuffer sql = new StringBuffer();
            sql.append("insert into tagent(name, email, description) values (?,?,?)");
            // 驱动
            Class.forName(driver);

            con = DriverManager.getConnection(url, user, password);
            // 关闭事务自动提交
            con.setAutoCommit(false);
            // 每10000次提交一次
            final int batchSize = 1000;
            int count = 0;
            Long startTime = System.currentTimeMillis();
            pst = con.prepareStatement(sql.toString());
            for (int i = 0; i < number; i++) {
                pst.setString(1, getRandomString(5, 15));
                pst.setString(2, getRandomEmail());
                pst.setString(3, getRandomString(10, 20));
                // 把一个SQL命令加入命令列表
                pst.addBatch();
                if (++count % batchSize == 0) {
                    pst.executeBatch();
                    con.commit();
                    System.out.println("===== " + i);
                    count = 0;
                }
            }
            // 执行批量更新
            pst.executeBatch();
            // 语句执行完毕，提交本事务
            con.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("用时：" + (endTime - startTime));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private static String getRandomString(int randomMin, int randomMax) {
        int[][] arr = {{48, 57}, {65, 90}, {97, 122}};
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int randomLength = random.nextInt(randomMax - randomMin) + randomMin;
        for (int j = 0; j < randomLength; j++) {
            int index = random.nextInt(3);
            int max = arr[index][1];
            int min = arr[index][0];
            char num = (char) (random.nextInt(max - min) + min);
            sb.append(num);
        }
        return sb.toString();
    }

    private static String getRandomEmail() {
        String[] emailSuffix = {"@163.com", "@gmail.com", "@qq.com", "@icloud.com"};
        Random random = new Random();
        int randomMax = random.nextInt(20 - 10) + 10;
        int randomMin = random.nextInt(10 - 5) + 5;
        String randomString = getRandomString(randomMin, randomMax);
        int randomSuffix = random.nextInt(4);
        return randomString + emailSuffix[randomSuffix];
    }
}