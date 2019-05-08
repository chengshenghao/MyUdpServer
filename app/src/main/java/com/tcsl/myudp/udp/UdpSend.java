package com.tcsl.myudp.udp;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 描述:发送udp广播，广播socket对应的端口号
 * <p/>作者：csh
 * <p/>创建时间: 2019/05/08 10:33
 */
public class UdpSend extends Thread {
    @Override
    public void run() {
        try {
            DatagramSocket udpSocket = new DatagramSocket(null);
            udpSocket.setReuseAddress(true);
            byte[] bytes = String.valueOf("9956").getBytes();
            DatagramPacket dataPacket = new DatagramPacket(bytes, bytes.length);
            dataPacket.setAddress(InetAddress.getByName("255.255.255.255"));
            dataPacket.setPort(9002);
            Log.e("ServerUdpSend", "发送数据");
            udpSocket.send(dataPacket);//发送数据
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
