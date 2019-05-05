package cn.fufu.kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class NumPartitioner implements Partitioner {
    private Map<String, ?> configs = null;
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        // s:topic; o:key; bytes:以字节码的格式存储key; o1:value; bytes1:以字节码的格式存储value
        // 返回值指定的分区值

        // 从传入的key中分割出用于分区的值
        int k = Integer.parseInt(o.toString());
        if (k<3) {
            return 0;
        }
        else if (k<6) {
            return 1;
        }
        else {
            return 2;
        }
    }

    public void close() {

    }

    public void configure(Map<String, ?> map) {
        this.configs = map;
    }
}
