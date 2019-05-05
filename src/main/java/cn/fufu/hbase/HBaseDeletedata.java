package cn.fufu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HBaseDeletedata {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        TableName tableName = TableName.valueOf("t6");
        if (!admin.tableExists(tableName)) {
            System.out.println("no table_" + tableName +" !");
            return;
        }
        Table table = connection.getTable(tableName);
        Delete delete = null;
        // 删除指定列族
        delete = new Delete(Bytes.toBytes("89064")); // rowkey
//        delete.addFamily(Bytes.toBytes("type"));
//        delete.addColumn(Bytes.toBytes("type"), Bytes.toBytes("age"));
        table.delete(delete);
        System.out.println("delete one rowkey");
        // 批量删除
        List<Delete> deleteList = new ArrayList<Delete>();
        delete = new Delete(Bytes.toBytes("zc56j")); // rowkey
        delete.addColumn(Bytes.toBytes("type"), Bytes.toBytes("name"));
        delete.addColumn(Bytes.toBytes("type"), Bytes.toBytes("city"));
        deleteList.add(delete);
        table.delete(deleteList);
        System.out.println("delete");

        if (table != null)
            table.close();
        if (admin != null)
            admin.close();
        if (connection != null)
            connection.close();
    }
}
