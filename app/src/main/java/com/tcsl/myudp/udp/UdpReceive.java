package com.tcsl.myudp.udp;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 描述:接收udp广播的线程，接收到广播以后，发送一个端口号的广播
 * <p/>作者：csh
 * <p/>创建时间: 2019/5/8 10:33
 */
public class UdpReceive extends Thread {

    private DatagramSocket udpSocket;

    @Override
    public void run() {
        try {
            udpSocket = new DatagramSocket(null);
            udpSocket.setReuseAddress(true);
            udpSocket.bind(new InetSocketAddress(9001));
            byte[] data = new byte[1024];
            DatagramPacket dataPacket = new DatagramPacket(data, data.length);
            while (true) {
                Log.e("ServerUdpReceive", "开启数据接收");
                udpSocket.receive(dataPacket);// 如果未收到数据，此句不会继续向下执行，一直处于监听状态
                Log.e("ServerUdpReceive", dataPacket.toString());
                new UdpSend().start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭线程
     */
    public void stopTask() {
        try {
            if (udpSocket != null) {
                udpSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
