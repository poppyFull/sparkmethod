package cn.fufu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class DirOrFile {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        Path path = new Path("/tmp/test/data.txt");
        if (!fs.exists(path)) {
            System.out.println(path.getName() + " dont exist!");
            return;
        }
        if (fs.isDirectory(path)) {
            System.out.println(path.getName() + " is directory");
            return;
        }
        if (fs.isFile(path)) {
            System.out.println(path.getName() + " is file");
            return;
        }

    }
}
