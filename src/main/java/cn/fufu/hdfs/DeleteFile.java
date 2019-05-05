package cn.fufu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class DeleteFile {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = org.apache.hadoop.fs.FileSystem.get(conf);
        Path path = new Path("/tmp/data.txt");
        fs.delete(path, true);
        System.out.println("delete file");

    }
}
