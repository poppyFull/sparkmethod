package cn.fufu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class ListFiles {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus: listStatus) {
            System.out.println(fileStatus);
            System.out.println(fileStatus.getPath());
            System.out.println(fileStatus.getPath().getName());
        }

    }
}
