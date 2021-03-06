package com.tcsl.myudp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tcsl.myudp.server.MyWebSocketServer;
import com.tcsl.myudp.udp.UdpReceive;
import com.tcsl.myudp.udp.UdpSend;

/**
 * 描述:服务端发送udp广播，广播socket对应的端口号
 * <p/>作者：csh
 * <p/>创建时间: 2019/05/08 10:33
 */
public class MainActivity extends AppCompatActivity {

    private UdpReceive udpReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //服务端开启广播接收
        udpReceive = new UdpReceive();
        udpReceive.start();
        //开启服务
        MyWebSocketServer.getInstance();
    }

    /**
     * 服务端发送UDP广播
     */
    public void send(View view) {
        new UdpSend().start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        udpReceive.stopTask();
        //停止socket服务监听，停止socket
        MyWebSocketServer.getInstance().closeConnect();
    }

    public void sendMessage(View view) {
        MyWebSocketServer.getInstance().sendMessage("hello world");
    }
}
