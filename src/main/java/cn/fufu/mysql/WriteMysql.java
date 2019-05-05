package cn.fufu.mysql;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class WriteMysql {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        Statement stmt = null;
        String sql = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();

            sql = "create table if not exists table1(id bigint(18) primary key auto_increment, content varchar(1000))auto_increment=1";
            stmt.execute(sql);

            FileReader fr = new FileReader(new File("/Users/fu/Documents/code/spark-data/spark-files/spark-files/file-1"));
            BufferedReader br = new BufferedReader(fr);
            String lines = null;
            while ((lines = br.readLine())!=null) {
                String content = lines.split("\\.")[0].trim();
                if (content.length()>0) {
                    sql = "insert into table1(content) values(\"" + content + "\")";
                    System.out.println(sql);
                    stmt.execute(sql);
                }
            }


            if (stmt!=null){
                stmt.close();
                conn.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
