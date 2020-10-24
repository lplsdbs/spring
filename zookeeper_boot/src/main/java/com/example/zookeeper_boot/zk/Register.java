package com.example.zookeeper_boot.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.nio.file.WatchEvent;

public class Register {
    private static final String SERVER_PATH = "/product";

    private static final String ZK_ADDRESS = "192.168.1.128:2181,192.168.1.129:2181,192.168.1.130:2181";

    private static final int ZK_TIMEOUT = 20000;

    private static ZooKeeper zk;
    private static ZooKeeperConnection zooKeeper;
    /**
     * 注册
     * @param address  地址
     */
    public void register(String address){
        try {
             zooKeeper = new ZooKeeperConnection();
             zk=zooKeeper.connect(ZK_ADDRESS);
             Stat stat = zk.exists(SERVER_PATH, false);
            if (stat == null) {
                zk.create(SERVER_PATH, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            String path = address;
            //创建短暂的可排序的子节点
            zk.create(SERVER_PATH+"/instance", path.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
