package cn.fufu.kafka;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.*;
import java.util.Properties;

public class KafkaProducerTest {
    private Properties properties = null;
    private Producer<String, String> producer = null;

    public KafkaProducerTest(Properties properties) {
        this.properties = properties;
    }

    // 不指定key
    public void write(String file, String topic) {
        if (producer == null) {
            producer = new KafkaProducer<String, String>(properties);
        }
        try {
            FileReader fr = new FileReader(new File(file));
            BufferedReader br = new BufferedReader(fr);
            String msg = null;
            while ((msg = br.readLine()) != null) {
                producer.send(new ProducerRecord<String, String>(topic, msg)); // 不指定key
                Thread.sleep(100);
            }
            br.close();
            fr.close();
            producer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 指定key
    public void writeWithKey(String file, String topic) {
        if (producer == null) {
            producer = new KafkaProducer<String, String>(properties);
        }
        try {
            FileReader fr = new FileReader(new File(file));
            BufferedReader br = new BufferedReader(fr);
            String msg = null;
            while ((msg = br.readLine()) != null) {
                String key = String.valueOf(RandomUtils.nextInt(9));
                producer.send(new ProducerRecord<String, String>(topic, key, msg)); // 指定key
                Thread.sleep(100);
            }
            br.close();
            fr.close();
            producer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.100.101:9092,192.168.100.102:9092,192.168.100.103:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 可设置生产者属性
        properties.put("acks", "-1"); // 所有副本收到消息，producer才收到broker确认
        properties.put("retries", 1);
        properties.put("batch.size", 16384); // 每批处理使用的内存字节数
        properties.put("linger.ms", 0); // 发送当前批次消息之前等待新消息的时间量
        String topic = "topic1";
//        KafkaProducerTest kp1 = new KafkaProducerTest(properties);
//        String file1 = "G:/spark-data/active_namenode/hadoop-hdfs-namenode-i186z19.log.1";
//        kp1.write(file, topic); // 不指定key，写入kafka
//        kp1.writeWithKey(file1, topic); // 指定key

        Properties properties1 = new Properties();
        properties1.put("bootstrap.servers", "192.168.100.101:9092,192.168.100.102:9092,192.168.100.103:9092");
        properties1.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties1.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties1.put("partitioner.class", "cn.fufu.kafka.NumPartitioner"); // 自定义partitioner
        KafkaProducerTest kp2 = new KafkaProducerTest(properties1);
        String file2 = "G:/spark-data/active_namenode/hadoop-hdfs-namenode-i186z19.log.2";
        kp2.writeWithKey(file2, topic);
    }
}
