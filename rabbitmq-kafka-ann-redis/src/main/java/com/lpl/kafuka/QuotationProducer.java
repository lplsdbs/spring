package com.lpl.kafuka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuotationProducer {
    //设置实例生成信息总数
    private static  final int MSG_SIZE=4;
    //需要手动创建
    private static  final String TOPIC="stock-qutation";

    private static final String BROKER_LIST="192.168.1.8:9092,192.168.1.9:9092";

    private static KafkaProducer<String,String>producer=null;
    static {
        producer=new KafkaProducer<String, String>(initConfig());
    }
    //初始化配置
    private static Properties initConfig(){
        Properties properties=new Properties();
        //不必全部broker，至少2个
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_LIST);
        //设置序列化类key
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //设置序列化实际数据类
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return properties;
    }
    private static StockQuotationInfo createQuotationInfo(){
        StockQuotationInfo stockQuotationInfo=new StockQuotationInfo();
        Random r=new Random();
        Integer stockCode=600100+r.nextInt(10);
        float random=(float)Math.random();
        if(random/2<0.5){
            random=-random;
        }
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        stockQuotationInfo.setCurrentPrice(Float.valueOf(decimalFormat.format(11+random)));
        stockQuotationInfo.setPreClosePrice(11.80f);
        stockQuotationInfo.setOpenPrice(11.5f);
        stockQuotationInfo.setLowPrice(10.5f);
        stockQuotationInfo.setHighPrice(12.5f);
        stockQuotationInfo.setStockCode(stockCode.toString());
        stockQuotationInfo.setTradeTime(System.currentTimeMillis());
        stockQuotationInfo.setStockName("股票-"+stockCode);
        return stockQuotationInfo;
    }
   @Test//单线程
    public  void simpleThread() {
        ProducerRecord<String,String>record=null;
        StockQuotationInfo stockQuotationInfo=null;
        try {
            int num=0;
            for(int i=0;i<MSG_SIZE;i++){
                stockQuotationInfo=createQuotationInfo();
                //stockQuotationInfo。toString作为消息类容
                record=new ProducerRecord<String, String>(TOPIC,null,stockQuotationInfo.getTradeTime(),stockQuotationInfo.getStockCode(),stockQuotationInfo.toString());
                //producer.send(record);//异步发送（不用等待结果）
                producer.send(record, new Callback() {//同步
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
                if(num++%10==0){
                    Thread.sleep(2000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
    //KafkaProducer是线程安全的
    @Test//多线程共享同一个producer,record
    public void multithreading(){
        ProducerRecord<String,String>record=null;
        StockQuotationInfo stockQuotationInfo=null;
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        long current=System.currentTimeMillis();
        try {
            for(int i=0;i<MSG_SIZE;i++) {
                stockQuotationInfo = createQuotationInfo();
                //stockQuotationInfo。toString作为消息类容
                record = new ProducerRecord<String, String>(TOPIC, null, stockQuotationInfo.getTradeTime(), stockQuotationInfo.getStockCode(), stockQuotationInfo.toString());
              //  producer.send(record);//异步发送（不用等待结果）
                executorService.submit(new KafkaProducerThread(producer,record));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
            executorService.shutdown();
        }
    }
}
