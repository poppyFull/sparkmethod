package cn.fufu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class GetFileSize {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = org.apache.hadoop.fs.FileSystem.get(conf);

        Path path = new Path("/tmp/test/data3");
        if (fs.isDirectory(path)) {
            System.out.println(fs.getContentSummary(path).getLength() + " bytes");
            return;
        }
        FileStatus[] listStatus = fs.listStatus(path);
        Long size = new Long(0);
        for (FileStatus fileStatus: listStatus) {
            size += fileStatus.getLen();
        }
        System.out.println(size + " bytes");

    }
}
