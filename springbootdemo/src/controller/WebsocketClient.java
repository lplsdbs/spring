package controller;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Author: songxiaoyan
 * Description:websocket客户端测试
 * Date: Created in 17:00 2019/5/8
 * Modified By:
 */
public class WebsocketClient {

    public static WebSocketClient client;

    private static String url = "ws://localhost:8099/websocket";
    public static void main(String[] args) throws InterruptedException {
        try {
            client = new WebSocketClient(new URI(url),new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println("打开链接");
                }

                @Override
                public void onMessage(String s) {
                    System.out.println("收到消息"+s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println("链接已关闭");
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                    System.out.println("发生错误已关闭");
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }

//        client.connect();
//        System.out.println(client.getDraft());
//        while(!client.getReadyState().equals(ReadyState.OPEN)){
//            System.out.println("正在连接");
//        }
//        System.out.println("打开了");
//        try {
//            String filename = "C:\\Users\\knowologe\\Desktop\\8k16bit.wav";
//            byte[] data=InputStream2ByteArray(filename);
////            for(int i=0;i<5;i++){
//            client.send(data);
////                Thread.sleep(1000);
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        client.close();
    }


    public static void send(byte[] bytes){
        client.send(bytes);
    }

    private static byte[] InputStream2ByteArray(String filePath) throws IOException {

        InputStream in = new FileInputStream(filePath);
        byte[] data = toByteArray(in);
        in.close();

        return data;
    }

    private static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

}
