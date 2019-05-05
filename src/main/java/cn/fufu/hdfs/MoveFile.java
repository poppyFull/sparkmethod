package cn.fufu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class MoveFile {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path p1 = new Path("/tmp/test/data.txt");
        Path p2 = new Path("/tmp/");
        fs.rename(p1, p2);
        System.out.println("file be moved");

    }
}
