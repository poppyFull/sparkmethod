package cn.fufu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class MakeDir {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        Path path = new Path("/tmp/test");
        if (fs.exists(path)) {
            System.out.println(path.getName() + " already exist!");
            return;
        }
        else {
            boolean rs = fs.mkdirs(path);
            System.out.println(rs);
        }

    }
}
