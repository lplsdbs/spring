package com.lpl.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class ZKGetChildren {
    private static ZooKeeper zk;
    private static ZooKeeperConnection conn;

    // Method to check existence of znode and its status, if znode is available.
    public static Stat znode_exists(String path) throws
            KeeperException,InterruptedException {
        return zk.exists(path,true);
    }

    public static void main(String[] args) throws InterruptedException,KeeperException {
        String path = "/MyFirstZnode"; // Assign path to the znode

        try {
            conn = new ZooKeeperConnection();
            zk = conn.connect("192.168.1.8:2181,192.168.1.9:2181,192.168.1.10:2181");
            Stat stat = znode_exists(path); // Stat checks the path

            if(stat!= null) {

                //“getChildren" method- get all the children of znode.It has two
                //args, path and watch
                //path - Znode路径。
                //
                //watcher - 监视器类型的回调函数。当指定的znode被删除或znode下的子节点被创建/删除时，ZooKeeper集合将进行通知。这是一次性通知。
                List<String> children = zk.getChildren(path, false);
                for(int i = 0; i < children.size(); i++)
                    System.out.println(children.get(i)); //Print children's
            } else {
                System.out.println("Node does not exists");
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
