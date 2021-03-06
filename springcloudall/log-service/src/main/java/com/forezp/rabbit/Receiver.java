package com.forezp.rabbit;

import com.alibaba.fastjson.JSON;
import com.forezp.entity.SysLog;
import com.forezp.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by fangzhipeng on 2017/7/12.
 */
@Component
public class Receiver {
//只有其他的线程完成了一些列的操作，然后释放信号其他被阻塞的信号才被唤醒
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    SysLogService sysLogService;
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        SysLog  sysLog=  JSON.parseObject(message,SysLog.class);
        sysLogService.saveLogger(sysLog);
        latch.countDown();
    }


}
