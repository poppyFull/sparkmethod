package cn.fufu.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerTest {
    private Properties properties = null;
    private KafkaConsumer<String, String> consumer = null;

    public KafkaConsumerTest(Properties properties) {
        this.properties = properties;
    }

    public void read(String topic) {
        if (consumer == null) {
            consumer = new KafkaConsumer<String, String>(properties);
        }
        consumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record: records) {
                System.out.println("partition: " + record.partition() + "\t key: " + record.key());
                System.out.println(record.value());
            }
        }
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.100.101:9092,192.168.100.102:9092,192.168.100.103:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "g-1");
//        properties.put("auto.offset.reset", "earliest");
//        properties.put("enable.auto.commit", "true");
//        properties.put("auto.commit.interval.ms", "1000");
//        properties.put("session.timeout.ms", "30000");
        KafkaConsumerTest kc = new KafkaConsumerTest(properties);
        String topic = "topic1";
        kc.read(topic);

    }

}
