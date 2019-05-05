package cn.fufu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class HBaseCreateTable {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        // 不指定splitkey建表
        TableName tableName = TableName.valueOf("t5");
        if (admin.tableExists(tableName)) {
            System.out.println(tableName + " already exist!");
        }
        else {
            HTableDescriptor desc = new HTableDescriptor(tableName);
            desc.addFamily(new HColumnDescriptor("private"));
            desc.addFamily(new HColumnDescriptor("public"));
            admin.createTable(desc);
            System.out.println("create table_" + tableName);
        }
        if (admin != null)
            admin.close();
        if (connection != null)
            connection.close();

    }
}
