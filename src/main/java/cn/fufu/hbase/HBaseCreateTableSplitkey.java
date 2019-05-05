package cn.fufu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HBaseCreateTableSplitkey {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        // 指定splitkey建表
        TableName tableName = TableName.valueOf("t6");
        byte[][] splitKeys = {
                Bytes.toBytes("333"),
                Bytes.toBytes("888"),
                Bytes.toBytes("ccc"),
                Bytes.toBytes("kkk"),
        };
        if (admin.tableExists(tableName)) {
            System.out.println(tableName + " already exists!");
        }
        else {
            HTableDescriptor desc = new HTableDescriptor(tableName);
            desc.addFamily(new HColumnDescriptor("type"));
            admin.createTable(desc, splitKeys);
            System.out.println("create table_" + tableName + " with splitkeys");
        }
        if (admin != null)
            admin.close();
        if (connection != null)
            connection.close();

    }
}
