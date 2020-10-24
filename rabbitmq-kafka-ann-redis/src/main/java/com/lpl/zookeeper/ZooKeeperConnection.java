package com.lpl.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZooKeeperConnection {
    // declare zookeeper instance to access ZooKeeper ensemble
    //声明zookeeper实例来访问zookeeper集成
    private ZooKeeper zoo;
    final CountDownLatch connectedSignal = new CountDownLatch(1);

    // Method to connect zookeeper ensemble.连接zookeeper系统的方法
    public ZooKeeper connect(String host) throws IOException,InterruptedException {
        //让我们创建一个新的帮助类 ZooKeeperConnection ，并添加一个方法 connect 。 connect 方法创建一个ZooKeeper对象，连接到ZooKeeper集合，然后返回对象。
        //这里 CountDownLatch 用于停止（等待）主进程，直到客户端与ZooKeeper集合连接。
        //ZooKeeper集合通过监视器回调来回复连接状态。一旦客户端与ZooKeeper集合连接，监视器回调就会被调用，并且监视器回调函数调用CountDownLatch的countDown方法来释放锁，在主进程中await。
        zoo = new ZooKeeper(host,5000,new Watcher() {

            public void process(WatchedEvent we) {

                if (we.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });

        connectedSignal.await();
        return zoo;
    }

    // Method to disconnect from zookeeper serverv 方法断开与zookeeper服务器的连接
    public void close() throws InterruptedException {
        zoo.close();
    }
}
