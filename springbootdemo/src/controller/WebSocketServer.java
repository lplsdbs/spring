package controller;
import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
@Slf4j
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //操作SSH的工具类
    private SSHAgent sshAgent;
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        log.info("客户端连接！");
        this.session = session;
        try {
            //在客户端通过webSockesendMessaget连接时，创建一个SSH的会话
            this.sshAgent = new SSHAgent();
            //这里写上你的远程服务器ip，账号密码。当然你可以抽取成配置文件
            this.sshAgent.initSession("192.168.43.248", "lpl", "lpl");
            //准备执行命令。
            sshAgent.execCommand(this);
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        this.sshAgent.close();
        log.info("有一连接关闭！");
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    /**
     * 收到客户端消息后调用的方法
     *
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息:" + message);
        //群发消息
        try {
            //通过工具类的标准输入网远程服务器中写内容
            this.sshAgent.printWriter.write( message+ "\r\n");
            this.sshAgent.printWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
}
