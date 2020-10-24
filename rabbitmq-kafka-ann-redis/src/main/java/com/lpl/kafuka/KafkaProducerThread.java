package com.lpl.kafuka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaProducerThread implements Runnable {
    private KafkaProducer<String,String>producer=null;
    private ProducerRecord<String,String>record=null;
    KafkaProducerThread(KafkaProducer<String,String>producer,ProducerRecord<String,String>record){
        this.producer=producer;
        this.record=record;
    }
    @Override
    public void run() {
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(null!=e){
                    e.printStackTrace();
                }
                if(recordMetadata!=null){
                    System.out.println(String.format("offset:%s,partition:%s",recordMetadata.offset(),recordMetadata.partition()));
                }
            }
        });
    }
}
