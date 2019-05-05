package cn.fufu.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;

public class HBasePrintResult {
    public void showCell(Result result) {
        Cell[] cells = result.rawCells();
        for (Cell cell: cells) {
            String rowkey = new String(CellUtil.cloneRow(cell));
            String timestamp = Long.toString(cell.getTimestamp());
            String family = new String(CellUtil.cloneFamily(cell));
            String col = new String(CellUtil.cloneQualifier(cell));
            String value= new String(CellUtil.cloneValue(cell));
            System.out.println("rowkey: " + rowkey + "\tfamily: " + family + "\tcol: " + col + "\tvalue: " + value);

        }
    }
}
