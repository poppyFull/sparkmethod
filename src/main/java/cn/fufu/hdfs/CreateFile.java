package cn.fufu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class CreateFile {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf); // 获取文件系统
        FSDataOutputStream outputStream = null;

        String filePath = "/tmp/test/data3";
        String contents = "aaaaaaaaaaa\n";
        Path path = new Path(filePath);

        if (!fs.exists(path)) {
            // 新建文件写入内容
            outputStream = fs.create(path); // 创建文件
            outputStream.write(contents.getBytes());
            outputStream.flush();
            System.out.println("create and write content");
        }
        else {
            // 追加写入到已有文件
            outputStream = fs.append(path);
            outputStream.write(contents.getBytes());
            System.out.println(path.getName() + " already exist and append content");
        }

        if (outputStream != null) {
            outputStream.close();
        }
        if (fs != null) {
            fs.close();
        }

    }
}
