package com.lpl.kafuka;

import kafka.admin.AdminUtils;
import kafka.admin.BrokerMetadata;
import kafka.tools.ConsoleProducer;
import kafka.utils.ZkUtils;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.security.JaasUtils;
import scala.collection.Map;
import scala.collection.Seq;

import java.util.Properties;

public class KafKaConnection {
    private static final int SESSION_TIMEOUT=30000;
    private static final int CONNECT_TIMEOUT=30000;
    private static final String ZK_CONNECT="192.168.1.8:2181,192.168.1.9:2181,192.168.1.10:2181";
    //返回类型为void如果不报错只是表示主题元数据在zk上面创建成功
    public static void createTopic(String topic, int partition, int repilca, Properties properties){
        ZkUtils zkUtils=null;
        try {
            zkUtils=ZkUtils.apply(ZK_CONNECT,SESSION_TIMEOUT,CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
            if(!AdminUtils.topicExists(zkUtils,topic)){
                AdminUtils.createTopic(zkUtils,topic,partition,repilca,properties,AdminUtils.createTopic$default$6());
//                AdminUtils.deleteTopic(zkUtils,topic);//删除主题
            }else{

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }

    }
    //添加分区
    public static void addPartition(){
        ZkUtils zkUtils=null;
        try {
            zkUtils=ZkUtils.apply(ZK_CONNECT,SESSION_TIMEOUT,CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
//            if(!AdminUtils.topicExists(zkUtils,topic)){
//                AdminUtils.createTopic(zkUtils,topic,partition,repilca,properties,AdminUtils.createTopic$default$6());
//            }else{
            //2个分区两个副本，第三个参数为添加分区之后的总分区数，第四个参数为副本的分配方案，包含已有的副本分配方案如下表示0分区副本在1，2机器，1分区在2，3机器上
            //区总数等于分区数*副本数
                AdminUtils.addPartitions(zkUtils,"partition-api-foo2",2,"1:2,2:3",true,AdminUtils.addPartitions$default$6());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }

    }
//分区副本重分配
    public static void createOrupdatePartition(){
        ZkUtils zkUtils=null;
        try {
            zkUtils=ZkUtils.apply(ZK_CONNECT,SESSION_TIMEOUT,CONNECT_TIMEOUT, JaasUtils.isZkSecurityEnabled());
            //获取代理元数据信息
            Seq<BrokerMetadata>brokerMetadataSeq=AdminUtils.getBrokerMetadatas(zkUtils,AdminUtils.getBrokerMetadatas$default$2(),AdminUtils.getBrokerMetadatas$default$3());
           //生成分区分配方案
            Map<Object,Seq<Object>> replica=AdminUtils.assignReplicasToBrokers(brokerMetadataSeq,3,3,AdminUtils.assignReplicasToBrokers$default$4(),AdminUtils.assignReplicasToBrokers$default$5());
            //修改分区副本分配方案
            AdminUtils.createOrUpdateTopicPartitionAssignmentPathInZK(zkUtils,"partition-api-foo2",replica,null,true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkUtils.close();
        }
    }
    public static void main(String[] args) {
//        addPartition();
//        createOrupdatePartition();
        Properties properties=new Properties();
//        properties.put("","");
        createTopic("part7",3,2,properties);
    }
}
