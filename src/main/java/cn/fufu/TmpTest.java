package cn.fufu;


import org.apache.commons.lang.math.RandomUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TmpTest {
    public static void main(String[] args) {
        System.out.println(String.valueOf(RandomUtils.nextInt(2)));
        System.out.println("asa".getBytes().toString());
        System.out.println(String.valueOf("asa".getBytes()));
        System.out.println(new String("asa".getBytes()));

        String url = "jdbc:mysql://localhost:3306/test01";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS tb2(id INT PRIMARY KEY, name VARCHAR(255))";
            stmt.execute(sql);


            if (stmt!=null){
                stmt.close();
                conn.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
