package com.tcsl.myudp.server;


import android.util.Log;


import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;


/**
 * 描述:socket服务器端，负责给电视棒发送设置信息以及叫号信息
 * <p/>作者：wjx
 * <p/>创建时间: 2017/10/10 9:54
 */
public class MyWebSocketServer extends WebSocketServer {
    public static final int PORT = 9956;
    private static MyWebSocketServer instance;

    private MyWebSocketServer() {
        super(new InetSocketAddress(PORT));
        start();
//        startKeep();
    }

    public static MyWebSocketServer getInstance() {
        if (instance == null) {
            synchronized (MyWebSocketServer.class) {
                if (instance == null) {
                    instance = new MyWebSocketServer();
                }
            }
        }
        return instance;
    }

//    private void startKeep() {
//        Observable.interval(5, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<Long>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        sendMessage(PushUtils.getKeep());
//                    }
//                });
//    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        Log.e("MyWebSocketServer", "接收端口打开" + conn.getRemoteSocketAddress().toString());
        Log.e("MyWebSocketServer", "接收端口打开" + conn);
    }


    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        Log.e("MyWebSocketServer", "onClose");
        Log.e("MyWebSocketServer", "onClose" + conn);
    }

    @Override
    public void onMessage(final WebSocket conn, String info) {
        Log.e("MyWebSocketServer", conn.getRemoteSocketAddress().toString() + info);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        Log.e("MyWebSocketServer", "onError" + ex.getMessage().toString());
    }

    @Override
    public void onStart() {

    }

    public void sendMessage(String str) {
//        String json = new Gson().toJson(bean);
        for (WebSocket socket : connections()) {
            Log.e("MyWebSocketServer", "sendMessageL:" + socket.getRemoteSocketAddress() + ":" + str);
            socket.send(str);
        }
    }

    public void closeConnect() {
        try {
            stop();
            instance = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
