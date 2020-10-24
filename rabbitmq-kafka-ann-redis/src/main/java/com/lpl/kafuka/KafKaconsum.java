package com.lpl.kafuka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class KafKaconsum {
    private static  final String TOPIC="stock-qutation";
    private static KafkaConsumer<String,String>kafkaConsumer;
    private static final String BROKER_LIST="192.168.1.8:9092,192.168.1.9:9092";
    public  static Properties getKafKaPerties(){
        Properties properties=new Properties();
        properties.put("bootstrap.servers",BROKER_LIST);
        properties.put("group.id","test");
//        properties.put("client.id","test");
        //消息key反序列化
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //消息value反序列化
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("enable.auto.commit",true);//偏移量自动提交，如果手动那么改为false
        return properties;
    }
    static {
        kafkaConsumer=new KafkaConsumer<String, String>(getKafKaPerties());
    }
    @Test
    public void subTopic(){
        //订阅主题
        kafkaConsumer.subscribe(Arrays.asList("stock-qutation"));
        //默认的自动提交偏移量
//         try {
//                while (true) {
//                    ConsumerRecords<String, String> consumerRecord = kafkaConsumer.poll(1000);
//                    for (ConsumerRecord<String, String> consumerRecords : consumerRecord) {
//                        System.out.println("partition" + consumerRecords.partition() + "offset" + consumerRecords.offset()
//                                + "key" + consumerRecords.key() + "value" + consumerRecords.value());
//                    }
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//            } finally {
//                kafkaConsumer.close();
//            }
        //订阅指定分区
        //kafkaConsumer.assign(Arrays.asList(new TopicPartition("stock-qutation",0),new TopicPartition("stock-qutation",1)));
       //kafka的消费者位移确认有自动和手动两种，默认是自动，手动又分为同步和异步，为了保证数据不被重复消费或者不丢失一般使用异步
        try {
            int minCommitSize=10;
            int icount=0;//消息计数
            while (true) {
                ConsumerRecords<String, String> consumerRecord = kafkaConsumer.poll(1000);
                for (ConsumerRecord<String, String> consumerRecords : consumerRecord) {
                    System.out.println("partition" + consumerRecords.partition() + "offset" + consumerRecords.offset()
                            + "key" + consumerRecords.key() + "value" + consumerRecords.value());
                icount++;
                }
                if(icount>=minCommitSize){
                    kafkaConsumer.commitAsync(new OffsetCommitCallback() {
                        @Override
                        public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                            if(null==e){
                                System.out.println("提交成功");
                            }else {
                                System.out.println("发生错误");
                            }
                        }
                    });
                }
            }


        }catch(Exception e){
            e.printStackTrace();
        } finally {
            kafkaConsumer.close();
        }
    }
    //以时间戳查询消息，就算消费过也会再次查询到
    @Test
    public void timStampToSearch(){
        kafkaConsumer.assign(Arrays.asList(new TopicPartition("stock-qutation",0)));
        try {
            Map<TopicPartition,Long>map=new HashMap<>();
            //构造查询分区
            TopicPartition topicPartition=new TopicPartition("stock-qutation",0);
            //查询12小时之前的
            map.put(topicPartition,(System.currentTimeMillis()-12*3600*1000));
            //返回时间大于等于查询时间的第一个偏移量
            Map<TopicPartition,OffsetAndTimestamp>offset=kafkaConsumer.offsetsForTimes(map);
            OffsetAndTimestamp offsetA=null;
            //查询所有分区
            for(Map.Entry<TopicPartition,OffsetAndTimestamp>entry:offset.entrySet()){
                offsetA=entry.getValue();
                if(null!=offsetA){
                    //重置消息起始偏移
                    kafkaConsumer.seek(topicPartition,entry.getValue().offset());
                }
            }
            while (true) {
                        ConsumerRecords<String, String> consumerRecord = kafkaConsumer.poll(1000);
                        for (ConsumerRecord<String, String> consumerRecords : consumerRecord) {
                            System.out.println("partition" + consumerRecords.partition() + "offset" + consumerRecords.offset()
                                    + "key" + consumerRecords.key() + "value" + consumerRecords.value());
                        }

                    }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaConsumer.close();
        }
    }
    @Test//支持多线程测试有问题待研究KafkaConsumer线程不安全
    public void mutilThread(){
        for(int i=0;i<6;i++){
            new KafKaConsumerThread(getKafKaPerties(),TOPIC,"第"+i+"个线程").start();
        }
    }
    //消费多线程是基于主题级别的消费，每个线程实例化一个kafkaConsumer对象，同组的消费者消费一个主题，如果同组的消费者数量大于分区数那么就会有闲置的消费者
    public static void main(String[] args) {
        for(int i=0;i<6;i++){
            new KafKaConsumerThread(getKafKaPerties(),TOPIC,"第"+i+"个线程").start();
        }
    }

}
