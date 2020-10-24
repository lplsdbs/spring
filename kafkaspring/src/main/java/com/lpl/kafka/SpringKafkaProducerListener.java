package com.lpl.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

public class SpringKafkaProducerListener implements ProducerListener<String,String> {
    @Override
    public void onSuccess(String topic, Integer pa, String key, String value, RecordMetadata recordMetadata) {
        System.out.println("委托成功：主题"+topic+"分区"+recordMetadata.partition()+"委托时间："+recordMetadata.timestamp()+"委托信息如下:");
        System.out.println(value);
    }

    @Override
    public void onError(String topic, Integer p, String key, String value, Exception e) {
        System.out.println("失败：topic"+topic+",value"+value+"exception:"+e.getLocalizedMessage());
    }

    @Override
    public boolean isInterestedInSuccess() {
        return true;//要onsuccess执行需要返回true
    }
}
