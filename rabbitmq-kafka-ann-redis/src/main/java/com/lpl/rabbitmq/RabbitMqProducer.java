package com.lpl.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RabbitMqProducer {
  private static final   String EXCHANGE_NAME="exchange_demo";
  private static  final String ROUTING_KEY="routingkey_demo";
  private static final String QUEUE_NAME="queue_demo";
  private static final String IP_ADDRESS="192.168.1.9";
  private  static   final int PORT=5672;
  static   Channel channel;
  static    Connection connection;
  static {
      ConnectionFactory factory=new ConnectionFactory();
      factory.setHost(IP_ADDRESS);
      factory.setPort(PORT);
      factory.setUsername("guest");
      factory.setPassword("guest");
      try {
           connection=factory.newConnection();
          channel=connection.createChannel();//创建信道
      } catch (IOException e) {
          e.printStackTrace();
      } catch (TimeoutException e) {
          e.printStackTrace();
      }
  }

    public static void main(String[] args)throws Exception {
      //路由键四种类型，还有一种hearts不常用
    //        ganout();//和此交换器绑定的队列都会收到消息
    //        direct();//路由键必须完全匹配才能发送到对应的队列
             topic();//和”。*“路由键后面匹配一个字母，和”。#“匹配多个字母的路由键
//        System.out.println(connection.isOpen());
//            alternateExchange();//备用交换器
    //        timeToLive1();//队列消息过期时间设置--统一设置
    //        timeToLive2();//给每个消息设置过期时间
    //        timeToLive3();//队列多久未使用则被删除
    //        dxl();
//        confirmSelect();
   }
    //发送方确认机制
    public static void confirmSelect()throws Exception{
        final   String EXCHANGE_NAME="exchange_confire";
        final String ROUTING_KEY="routingkey_confire";//路由键“.#"匹配多个单词
        final String QUEUE_NAME="queue_confire";
        //创建一个持久化的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        //创建持久化，非排他，非自动删除的队列
        Map<String,Object>map=new HashMap<String, Object>();
        map.put("x-message-ttl",6000);//队列中所有的消息过期时间都是6秒，如果设置为0那么要是没有消费者消费就直接被丢弃
        channel.queueDeclare(QUEUE_NAME,true,false,false,map);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //发送一条持久化信息
        String message="hello word3g";
        channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY,true, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        channel.confirmSelect();//设置成确认模式
        //线程需要等待一下要不然返回的收不到
        channel.addReturnListener(new ReturnListener() {//basicPublish的3参数为true那么如果交换器根据路由键找不到队列那么就会把数据返回而不是丢弃
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("返回结果是："+new String(bytes));
            }
        });
        boolean confirem=channel.waitForConfirms();
        System.out.println(confirem);
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }




    //被reject-false/ttl/队列过长被丢弃到死信队列（ttl和死信xdl可以模拟延时队列--消费者消费对应的死信队列即可）
    public static void dxl()throws Exception{
        final   String EXCHANGE_normal="exchange_normal_dxl";
        final   String EXCHANGE_dxl="exchange_dxl";
        final String dxl_KEY="dxl_key";//路由键“.#"匹配多个单词
        final String QUEUE_normal="queue_normal_dxl";
        final String QUEUE_dxl="queue_dxl";
        //创建一个持久化的交换器
        channel.exchangeDeclare(EXCHANGE_normal,"fanout",true,false,null);
        channel.exchangeDeclare(EXCHANGE_dxl,"direct",true,false,null);
        //创建持久化，非排他，非自动删除的队列
        Map<String,Object>map=new HashMap<String, Object>();
        map.put("x-message-ttl",6000);//队列中所有的消息过期时间都是6秒，如果设置为0那么要是没有消费者消费就直接被丢弃
        map.put("x-dead-letter-exchange",EXCHANGE_dxl);// 死信队列的交换器
        map.put("x-dead-letter-routing-key",dxl_KEY);// 死信队列的路由键
        channel.queueDeclare(QUEUE_normal,true,false,false,map);//消息过期之后被丢弃到私信交换器，然后匹配路由键到死刑队列
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_normal,EXCHANGE_normal,"");
        channel.queueDeclare(QUEUE_dxl,true,false,false,null);//声明死信队列
        channel.queueBind(QUEUE_dxl,EXCHANGE_dxl,dxl_KEY);

        //发送一条持久化信息
        String message="hello word3g";
        channel.basicPublish(EXCHANGE_normal,"",true, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        channel.txSelect();//开启事务
        //线程需要等待一下要不然返回的收不到
        channel.addReturnListener(new ReturnListener() {//basicPublish的3参数为true那么如果交换器根据路由键找不到队列那么就会把数据返回而不是丢弃
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("返回结果是："+new String(bytes));
            }
        });
        //关闭事务
        channel.txCommit();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            channel.txRollback();//回滚事务
        }
        channel.close();
        connection.close();
    }
    //队列消息过期时间设置--统一设置
    public static void timeToLive1()throws Exception{
        final   String EXCHANGE_NAME="exchange_ttl";
        final String ROUTING_KEY="routingkey_ttl";//路由键“.#"匹配多个单词
        final String QUEUE_NAME="queue_ttl";
        //创建一个持久化的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        //创建持久化，非排他，非自动删除的队列
        Map<String,Object>map=new HashMap<String, Object>();
        map.put("x-message-ttl",6000);//队列中所有的消息过期时间都是6秒，如果设置为0那么要是没有消费者消费就直接被丢弃
        channel.queueDeclare(QUEUE_NAME,true,false,false,map);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //发送一条持久化信息
        String message="hello word3g";
        channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY,true, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        //线程需要等待一下要不然返回的收不到
        channel.addReturnListener(new ReturnListener() {//basicPublish的3参数为true那么如果交换器根据路由键找不到队列那么就会把数据返回而不是丢弃
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("返回结果是："+new String(bytes));
            }
        });
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
    //队列多久未使用则删除时间设置x-expires--删除不及时
    public static void timeToLive3()throws Exception{
        final   String EXCHANGE_NAME="exchange_ttl3";
        final String ROUTING_KEY="routingkey_ttl3";//路由键“.#"匹配多个单词
        final String QUEUE_NAME="queue_ttl3";
        //创建一个持久化的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        //创建持久化，非排他，非自动删除的队列
        //队列多久未使用则删除时间设置x-expires
        Map<String,Object>map=new HashMap<String, Object>();
        map.put("x-erpires",6000);
        channel.queueDeclare(QUEUE_NAME,true,false,false,map);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //发送一条持久化信息
        String message="hello word3g";

        channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY,true, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        //线程需要等待一下要不然返回的收不到
        channel.addReturnListener(new ReturnListener() {//basicPublish的3参数为true那么如果交换器根据路由键找不到队列那么就会把数据返回而不是丢弃
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("返回结果是："+new String(bytes));
            }
        });
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
    //队列消息过期时间设置--每个单独设置（这种过期机制是时间一到就从队列中删除）
    public static void timeToLive2()throws Exception{
        final   String EXCHANGE_NAME="exchange_ttl2";
        final String ROUTING_KEY="routingkey_ttl2";//路由键“.#"匹配多个单词
        final String QUEUE_NAME="queue_ttl2";
        //创建一个持久化的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        //创建持久化，非排他，非自动删除的队列
//        Map<String,Object>map=new HashMap<String, Object>();
//        map.put("x-message-ttl",6000);//队列中所有的消息过期时间都是6秒，如果设置为0那么要是没有消费者消费就直接被丢弃
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //发送一条持久化信息
        String message="hello word3g";
        //给每个消息设置过期时间
        AMQP.BasicProperties.Builder builder=new AMQP.BasicProperties.Builder();
        builder.deliveryMode(2);//持久化
        builder.expiration("6000");//6秒
        AMQP.BasicProperties properties=builder.build();
        channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY,true, properties,message.getBytes());
        //线程需要等待一下要不然返回的收不到
        channel.addReturnListener(new ReturnListener() {//basicPublish的3参数为true那么如果交换器根据路由键找不到队列那么就会把数据返回而不是丢弃
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("返回结果是："+new String(bytes));
            }
        });
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
    //备胎交换器
    public static void alternateExchange()throws Exception{
        final   String EXCHANGE_NAME="exchange_normal";
        final   String EXCHANGE_AE="exchange_AE";
        final String ROUTING_KEY="normal_routing";
        final String QUEUE_NAME="normal_queue";
        final String QUEUE_AE="ae_queue";
        Map<String,Object>map=new HashMap<String, Object>();
        //声明备用交换器的时候用alternate-exchange参数
        map.put("alternate-exchange",EXCHANGE_AE);
        //声明主交换器，带参备用交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"topic",true,false,map);
        //声明备用交换器，注意声明为fanout类型的
        channel.exchangeDeclare(EXCHANGE_AE,"fanout",true,false,null);
        //声明主队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //声明备用队列
        channel.queueDeclare(QUEUE_AE,true,false,false,null);
       //绑定主队列和交换器
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //绑定备用交换器和队列
        channel.queueBind(QUEUE_AE,EXCHANGE_AE,"");
        String message="hello word3g";
        //如果根据路由键找不到队列那么就会存到备用队列
        channel.basicPublish(EXCHANGE_NAME,"routingkey_topic.lpl",true, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        channel.addReturnListener(new ReturnListener() {//basicPublish的3参数为true那么如果交换器根据路由键找不到队列那么就会把数据返回而不是丢弃
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("返回结果是："+new String(bytes));
            }
        });
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
    public static void topic()throws Exception{
        final   String EXCHANGE_NAME="exchange_topic#";
//        final String ROUTING_KEY="routingkey_topic.*";//路由键“.*"匹配一个单词
        final String ROUTING_KEY="routingkey_topic.#";//路由键“.#"匹配多个单词
        final String QUEUE_NAME="queue_topic#";
        //创建一个持久化的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"topic",true,false,null);
        //创建持久化，非排他，非自动删除的队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //发送一条持久化信息
        String message="hello word3g";
//                super.run();
                    channel.basicPublish(EXCHANGE_NAME,"",true,MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

//        channel.basicPublish(EXCHANGE_NAME,"routingkey_topic.l",true, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
            //线程需要等待一下要不然返回的收不到
                channel.addReturnListener(new
                 ReturnListener() {//basicPublish的3参数为true那么如果交换器根据路由键找不到队列那么就会把数据返回而不是丢弃
                public void handleReturn ( int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties,
                byte[] bytes) throws IOException {
                    System.out.println("返回结果是：" + new String(bytes));
                }
                });

        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
    public static void direct()throws Exception{
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();//创建信道
        //创建一个持久化的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        //创建持久化，非排他，非自动删除的队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //发送一条持久化信息
        String message="hello word3g";
        channel.basicPublish(EXCHANGE_NAME,"hjhh",true, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        //线程需要等待一下要不然返回的收不到
        channel.addReturnListener(new ReturnListener() {//basicPublish的3参数为true那么如果交换器根据路由键找不到队列那么就会把数据返回而不是丢弃
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("返回结果是："+new String(bytes));
            }
        });
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
    public static void ganout()throws Exception{
         final   String EXCHANGE_NAME="exchange_fanout";
          final String ROUTING_KEY="routingkey_fanout";
         final String QUEUE_NAME="queue_fanout";
        final String IP_ADDRESS="192.168.1.9";
           final int PORT=5672;
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();//创建信道
        //创建一个持久化的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout",true,false,null);
        //创建持久化，非排他，非自动删除的队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //发送一条持久化信息
        String message="hello word3fan";
        channel.basicPublish(EXCHANGE_NAME,"",true, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        channel.addReturnListener(new ReturnListener() {//basicPublish的3参数为true那么如果交换器根据路由键找不到队列那么就会把数据返回而不是丢弃
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("返回结果是："+new String(bytes));
            }
        });
        TimeUnit.SECONDS.sleep(1);

        channel.close();
        connection.close();
    }
}
//rabbitmqctl add_vhost vhost1--添加虚拟主机
//rabbitmqctl list_vhosts --列出虚拟主机
//rabbitmqctl delete_vhost vhost1--删除
//rabbitmqctl set_permissions -p vhost1 root ".*" ".*" ".*"--授予用户可以访问vhost1配置，读写权限，可以正则匹配资源
//rabbitmqctl clear_permissions -p vhost1 root --清除用户权限
//rabbitmqctl list_permissions -p vhost1--虚拟机上的权限
//rabbitmqctl list_user_permissions root--用户的权限

//权限是以vhost为单位的，用户通常被指定给至少一个vhost
//rabbitmqctl add_user root root--添加用户
//rabbitmqctl change_password root lpls--修改mima
//rabbitmqctl authenticate_user root root2/--用root2用户验证root
//rabbitmqctl delete_user root
//rabbitmqctl list_users
//rabbitmqctl set_user_tags root xxxxx//给用户指定jues
//none什么角色没有
//management 访问web管理页面
//policymaker 包含web并且可以管理策略
//monitoring 包含web可以看到所有连接，信道
//administrator 最高权限


