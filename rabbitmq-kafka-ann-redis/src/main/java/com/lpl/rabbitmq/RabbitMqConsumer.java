package com.lpl.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RabbitMqConsumer {
    private static final   String QUEUE_NAME="test.mirror";
    private static final String IP_ADDRESS="192.168.1.9";
    private  static   final int PORT=5672;

    public static void main(String[] args)throws Exception {
        Address[]addresses=new Address[]{new Address(IP_ADDRESS,PORT),new Address("192.168.1.10",5672),new Address("192.168.1.8",5672)};
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        Connection connection=factory.newConnection(addresses);
        final Channel channel=connection.createChannel();
        channel.basicQos(64);
        Consumer consumer=new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("recv message:"+new String(body));
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //显示的回复服务器确认收到消息
                channel.basicAck(envelope.getDeliveryTag(),false);
                //channel.basicReject(envelope.getDeliveryTag(),false);//false表示拒绝之后在队列中移除，true则是放回队列
            }
        };

//        Consumer consumer=new MyConsum(channel);
        channel.basicConsume(QUEUE_NAME,false,consumer);
//        while (true){
//            channel.basicConsume(QUEUE_NAME,false,"de",new DefaultConsumer(channel){
//
//                @Override
//                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    System.out.println("recv message:"+new String(body));
//                    try {
//                        TimeUnit.SECONDS.sleep(3);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    //显示的回复服务器确认收到消息
//                    channel.basicAck(envelope.getDeliveryTag(),false);
//                    //channel.basicReject(envelope.getDeliveryTag(),false);//false表示拒绝之后在队列中移除，true则是放回队列
//                }
//            });
        TimeUnit.SECONDS.sleep(10);//这里需要等待消息签收
//        }
        //s1 消费者标签和别的区分b为是否自动确认，false不自动确认
//        TimeUnit.SECONDS.sleep(50);
//        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
}
