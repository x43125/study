package com.wx.sqlstudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Author: x43125
 * @Date: 22/04/07
 */
class IndexStudy01 {
    static String url = "jdbc:postgresql://192.168.11.128:5432/postgres";
    static String usr = "postgres";
    static String psd = "123ABCdef*";
    static String driver = "org.postgresql.Driver";

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("开始");
        Connection conn = null;

        try {
            Class.forName(driver);
            System.out.println("建立连接");
            conn = DriverManager.getConnection(url, usr, psd);
            System.out.println("建立成功");
            Statement st = conn.createStatement();
            System.out.println("准备查询");
//            ResultSet rs = st.executeQuery("SELECT * FROM tagent");
//            ResultSet rs = st.executeQuery("SELECT * FROM tagent where id = 31865");
//            ResultSet rs = st.executeQuery("SELECT * FROM tagent WHERE name='QTIY1' AND email = '6278P5YW6@icloud.com'");
//            ResultSet rs = st.executeQuery("SELECT * FROM tagent WHERE email = '6278P5YW6@icloud.com'");
//            ResultSet rs = st.executeQuery("SELECT * FROM tagent WHERE name='QTIY1'");
//            ResultSet rs = st.executeQuery("SELECT * FROM tagent WHERE name like ('%TIY%') AND email = '6278P5YW6@icloud.com'");
            ResultSet rs = st.executeQuery("SELECT * FROM tagent WHERE name like ('%QTI%')");

            System.out.println("查询结束 --------------------------------------------");

            while (rs.next()) {
//                System.out.println(rs.getInt("count"));
                System.out.println("id: " + rs.getLong("id"));
                System.out.println("name: " + rs.getString("name"));
                System.out.println("birthday: " + rs.getString("birthday"));
                System.out.println("email: " + rs.getString("email"));
                String description = rs.getString("description");
                System.out.println("description: " + (description.length() < 30 ? description : description.substring(0, 30)) + " ......");
                System.out.println("create_time: " + rs.getTimestamp("create_time"));
                System.out.println("update_time: " + rs.getTimestamp("update_time"));
                System.out.println("---------------------------------------------");
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println("结束: 耗时: " + (System.currentTimeMillis() - startTime) + "ms --------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
