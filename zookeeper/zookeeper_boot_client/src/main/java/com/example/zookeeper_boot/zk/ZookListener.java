package com.example.zookeeper_boot.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.proto.WatcherEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ZookListener {
    private static final String SERVER_PATH = "/product";

    private static final String ZK_ADDRESS = "192.168.1.128:2181,192.168.1.129:2181,192.168.1.130:2181";

    private static final int ZK_TIMEOUT = 20000;

    private ZooKeeper zooKeeper;

    private List<String> paths = new LinkedList<>();
    private static ZooKeeperConnection zooKeeperc;

    public void init(){
//        是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        try {
            zooKeeper = new ZooKeeper(ZK_ADDRESS, ZK_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                        //将count值减1
                        countDownLatch.countDown();
                    }
                    //监听该节点的变化，如果节点出现变化，则重新获取节点下的ip和端口
                    if(watchedEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged &&
                            watchedEvent.getPath().equals(SERVER_PATH)){
                        System.out.println("监听到的消息："+watchedEvent.getType()+" "+watchedEvent.getPath());
                        getChilds();
                    }

                }

            });
            //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行,zk连接需要时间
            countDownLatch.await();
            getChilds();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private  void  getChilds(){
        List<String> ips =new LinkedList<>();
        try {
            //添加监听
            List<String> childs = this.zooKeeper.getChildren(SERVER_PATH, true);
            for(String child : childs){
                byte[] obj = zooKeeper.getData(SERVER_PATH+"/"+child,false,null);
                String path = new String(obj,"utf-8");
                ips.add(path);
            }
            this.paths = ips;
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public String  getPath(){
        if(paths.isEmpty()){
            return null;
        }
        //这里我们随机获取一个ip端口使用
        int index =  new Random().nextInt(paths.size());
        return paths.get(index);
    }

}
