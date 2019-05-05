package cn.fufu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class DeleteDir {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = org.apache.hadoop.fs.FileSystem.get(conf);
        Path path = new Path("/tmp/test/");
        fs.delete(path, true); // true：递归删除目录
        System.out.println("delete dir");
    }
}
