package cn.fufu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HBaseGetData {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        // 精确查询数据
        TableName tableName = TableName.valueOf("t6");
        if (!admin.tableExists(tableName)) {
            System.out.println("no table_" + tableName);
            return;
        }
        Table table = connection.getTable(tableName);
        Get get = null;
        Result result = null;
        // 按rowkey查询
        System.out.println("按rowkey查询");
        get = new Get(Bytes.toBytes("ee73g")); // rowkey
        result = table.get(get);
        new HBasePrintResult().showCell(result);
        // 按rowkey，col查询
        get = new Get(Bytes.toBytes("ee73g"));
        get.addColumn(Bytes.toBytes("type"), Bytes.toBytes("name")); // addColumn(family,col)
        get.addColumn(Bytes.toBytes("type"), Bytes.toBytes("city")); // addColumn(family,col)
        result = table.get(get);
        System.out.println("按rowkey col 查询");
        new HBasePrintResult().showCell(result);
        if (table != null)
            table.close();
        if (admin != null)
            admin.close();
        if (connection != null)
            connection.close();

    }
}
