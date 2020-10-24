package com.lpl.kafuka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class KafKaConsumerThread extends Thread{
    private KafkaConsumer<String,String>kafkaConsumer;
    KafKaConsumerThread(Properties properties,String topic,String name){
        super(name);
        kafkaConsumer=new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
       while (true){
           ConsumerRecords<String,String>consumerRecords=kafkaConsumer.poll(1000);
           for(ConsumerRecord<String,String>consumerRecord:consumerRecords){
               System.out.println("threadName  :"+Thread.currentThread().getName()+"pa  "+consumerRecord.partition()+"off  "+consumerRecord.offset()+"key "+consumerRecord.key()+" value"+consumerRecord.value());
           }
       }
    }
}
