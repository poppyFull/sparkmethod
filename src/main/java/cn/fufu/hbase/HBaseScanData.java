package cn.fufu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HBaseScanData {
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
        Scan scan = null;
        ResultScanner resultScanner = null;
        // scan查看表全部数据
        scan = new Scan();
        System.out.println("scan表 table_" + tableName +" 全部数据");
        resultScanner = table.getScanner(scan);
        for (Result result: resultScanner) {
            new HBasePrintResult().showCell(result);
        }
        // scan查看表指定范围数据
        scan = new Scan();
        System.out.println("scan表 table_" + tableName +" 指定范围数据");
        scan.setStartRow(Bytes.toBytes("0")); // rowkey
        scan.setStopRow(Bytes.toBytes("9"));
        scan.addColumn(Bytes.toBytes("type"), Bytes.toBytes("name")); // addColumn(family,col)
        resultScanner = table.getScanner(scan);
        for (Result result: resultScanner) {
            new HBasePrintResult().showCell(result);
        }
        // scan过滤表数据
        scan = new Scan();
        System.out.println("scan表 table_" + tableName +" 过滤后数据");
        FilterList filterList = new FilterList();
        SingleColumnValueFilter filter = null;
        RegexStringComparator comp = new RegexStringComparator("an"); //查找包含指定字符串的数据
        filter = new SingleColumnValueFilter(Bytes.toBytes("type"),
                Bytes.toBytes("city"), CompareFilter.CompareOp.EQUAL, comp);
        filterList.addFilter(filter);
        scan.addColumn(Bytes.toBytes("type"), Bytes.toBytes("city")); // 只返回指定的cell，同一行中其他cell不返回
        scan.setFilter(filterList);
        resultScanner = table.getScanner(scan);
        for (Result result: resultScanner) {
            new HBasePrintResult().showCell(result);
        }

        if (table != null)
            table.close();
        if (admin != null)
            admin.close();
        if (connection != null)
            connection.close();
    }
}
