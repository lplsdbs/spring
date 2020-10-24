package com.lpl.kafka;

        import org.apache.kafka.clients.consumer.ConsumerRecord;
        import org.springframework.kafka.listener.MessageListener;

public class SpringKafkaConsumerListener implements MessageListener<String,String> {
    @Override
    public void onMessage(ConsumerRecord<String,String> data) {
        System.out.println("消费线程："+Thread.currentThread().getName()+"主题："+data.topic()+"委托时间"+data.timestamp()+"消息如下");
        System.out.println(data.key()+"  "+data.value());
    }
}
