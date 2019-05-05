package cn.fufu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HBasePutData {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        TableName tableName = TableName.valueOf("t6");
        if (!admin.tableExists(tableName)) {
            System.out.println("no table_" + tableName);
            return;
        }
        Table table = connection.getTable(tableName);
        // 插入单条数据
        Put put = new Put(Bytes.toBytes("28956")); // rowkey
        put.addColumn(Bytes.toBytes("type"), Bytes.toBytes("name"), Bytes.toBytes("LA")); // addColumn(family,col,value)
        table.put(put);
        // 批量插入数据
        List<Put> putList = new ArrayList<Put>();
        Put p = null;
        p = new Put(Bytes.toBytes("ee73g")); // rowkey
        p.addColumn(Bytes.toBytes("type"), Bytes.toBytes("name"), Bytes.toBytes("GG")); // addColumn(family,col,value)
        p.addColumn(Bytes.toBytes("type"), Bytes.toBytes("age"), Bytes.toBytes("16"));
        p.addColumn(Bytes.toBytes("type"), Bytes.toBytes("city"), Bytes.toBytes("Beijing"));
        putList.add(p);
        p = new Put(Bytes.toBytes("89064")); // rowkey
        p.addColumn(Bytes.toBytes("type"), Bytes.toBytes("name"), Bytes.toBytes("K")); // addColumn(family,col,value)
        p.addColumn(Bytes.toBytes("type"), Bytes.toBytes("age"), Bytes.toBytes("30")); // addColumn(family,col,value)
        p.addColumn(Bytes.toBytes("type"), Bytes.toBytes("city"), Bytes.toBytes("Shanghai")); // addColumn(family,col,value)
        putList.add(p);
        p = new Put(Bytes.toBytes("klp19")); // rowkey
        p.addColumn(Bytes.toBytes("type"), Bytes.toBytes("name"), Bytes.toBytes("PPi")); // addColumn(family,col,value)
        p.addColumn(Bytes.toBytes("type"), Bytes.toBytes("age"), Bytes.toBytes("60")); // addColumn(family,col,value)
        p.addColumn(Bytes.toBytes("type"), Bytes.toBytes("city"), Bytes.toBytes("Guzhangzhou")); // addColumn(family,col,value)
        putList.add(p);
        table.put(putList);
        System.out.println("put data to table_" + tableName);
        if (table != null)
            table.close();
        if (admin != null)
            admin.close();
        if (connection != null)
            connection.close();

    }
}
