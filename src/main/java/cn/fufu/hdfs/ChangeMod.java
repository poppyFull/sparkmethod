package cn.fufu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;

import java.io.IOException;

public class ChangeMod {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        FileStatus[] listStatus = null;
        FsPermission permission = null;
        Path path = null;

        path = new Path("/tmp");
        listStatus = fs.listStatus(path);
        // 查看文件权限
        for (FileStatus fileStatus: listStatus) {
            permission = fileStatus.getPermission();
            System.out.println(fileStatus.getPath().getName() + " : " + permission + "\t owner: " +fileStatus.getOwner() + "\t group: " + fileStatus.getGroup());
//            System.out.println("owner: " + permission.getUserAction() + "\t group: " + permission.getGroupAction() + "\t other: " + permission.getOtherAction());
        }

        // 修改文件(夹)权限
        path = new Path("/tmp/test/");
        permission = new FsPermission(FsAction.ALL, FsAction.ALL, FsAction.ALL); // user, group, other
        fs.setPermission(path, permission);
        System.out.println("chmod 777 " + path.toString());

        // 修改目录下全部文件权限
        String pathStr = "/tmp/test/";
        path = new Path(pathStr);
        listStatus = fs.listStatus(path);
        for (FileStatus fileStatus: listStatus) {
            Path p = new Path(pathStr + fileStatus.getPath().getName());
            permission = new FsPermission(FsAction.ALL, FsAction.ALL, FsAction.ALL); // user, group, other
            fs.setPermission(p, permission);
            System.out.println("chmod 777 " + p.toString());
            System.out.println("chmod 777 " + p.toString());
        }

    }
}
