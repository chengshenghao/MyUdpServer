package com.tcsl.myudp.server;


import android.util.Log;


import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;


/**
 * 描述:socket服务器端，负责给电视棒发送设置信息以及叫号信息
 * <p/>作者：wjx
 * <p/>创建时间: 2017/10/10 9:54
 */
public class MyWebSocketServer extends WebSocketServer {
    public static final int PORT = 9956;
    private static MyWebSocketServer instance;
//    private Gson gson;

    private MyWebSocketServer() {
        super(new InetSocketAddress(PORT));
        start();
//        gson = new Gson();
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
//        BasePushBean<PushSettingBean> setting = PushUtils.getSetting();
//        sendMessage(conn, setting);
//        BasePushBean<PwdBean> pwd = PushUtils.getPwdBean();
//        sendMessage(conn, pwd);
//        PushUtils.getWait().subscribe(new Action1<BasePushBean<PushWaitBean>>() {
//            @Override
//            public void call(BasePushBean<PushWaitBean> bean) {
//                sendMessage(bean);
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//
//            }
//        });
    }


    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        Log.e("MyWebSocketServer", "onClose");
        Log.e("MyWebSocketServer", "onClose" + conn);
    }

    @Override
    public void onMessage(final WebSocket conn, String info) {
        Log.e("MyWebSocketServer", conn.getRemoteSocketAddress().toString() + info);
//        BasePushBean base = gson.fromJson(info, BasePushBean.class);
//        if (PushConstants.PUSH_GET_NUM.equals(base.getPushAction())) {
//            Type type = new TypeToken<BasePushBean<TakeBean>>() {
//            }.getType();
//            BasePushBean<TakeBean> bean = gson.fromJson(info, type);
//            final QueueBean queueBean = BeanUtil.getNum(bean.getData().getPhone(), Integer.valueOf(bean.getData().getPeo()));
//            if (UpdateQueue.getInstance().takeNo(queueBean)) {
//                // 上传排队情况
//                TaskManager.getInstance().newPutQueueStateTask();
//                TaskManager.getInstance().executeRunnable(TaskManager.getInstance().getPutQueueStateRunnable());
//                if (bean.getData().getPrint()) {
//                    //打印小票
//                    sendMessage(conn,PushUtils.getOkMsgBean(queueBean.getQueueName() + ":取号成功"));
//                    new PrintBeanUtils().getPrintList(queueBean).subscribe(new Action1<PrintBean>() {
//                        @Override
//                        public void call(PrintBean printBean) {
//                            QueuePrintManager.getInstance().print(printBean, new OnPrinterListener() {
//                                @Override
//                                public void onDeviceNotBound() {
//                                    sendMessage(PushUtils.getOkMsgBean("未绑定打印机"));
//                                }
//
//                                @Override
//                                public void onConnectFailed() {
//                                    sendMessage(PushUtils.getOkMsgBean("打印机连接失败"));
//                                }
//
//                                @Override
//                                public void onPrintError(String msg) {
//                                    sendMessage(PushUtils.getOkMsgBean("单据打印失败"));
//                                }
//                            });
//                        }
//                    });
//                } else {
//                    new PrintBeanUtils().getPushPrintBean(queueBean).subscribe(new Action1<PrintBean>() {
//                        @Override
//                        public void call(PrintBean printBean) {
//                            sendMessage(conn, PushUtils.getPrintMsg(printBean));
//                        }
//                    });
//                }
//            } else {
//                sendMessage(PushUtils.getFailedMsgBean("取号失败"));
//            }
//        } else if (PushConstants.PUSH_TEST_PRINT.equals(base.getPushAction())) {
//            //测试打印
//            QueuePrintManager.getInstance().testPrint(new PrintSettingModel().getTestPrintBean(LocalXml.getLogoPath(), LocalXml.getMcName(), LocalXml.getPreferText(), LocalXml.getAdvertiseText(),
//                    LocalXml.getQueueUrl(), LocalXml.getPrintPassTipText(), LocalXml.getPrintPassTip())
//                    , new OnPrinterListener() {
//                        @Override
//                        public void onDeviceNotBound() {
//                            sendMessage(PushUtils.getFailedMsgBean("未绑定打印机"));
//                        }
//
//                        @Override
//                        public void onConnectFailed() {
//                            sendMessage(PushUtils.getFailedMsgBean("打印机连接失败"));
//                        }
//
//                        @Override
//                        public void onPrintError(String msg) {
//                            sendMessage(PushUtils.getFailedMsgBean(msg));
//                        }
//                    });
//        } else if (PushConstants.PUSH_OK.equals(base.getPushAction())) {
//        } else if (PushConstants.PUSH_PRINT.equals(base.getPushAction())) {
//            //进行数据的打印
//        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        Log.e("MyWebSocketServer", "onError" + ex.getMessage().toString());
    }

    @Override
    public void onStart() {

    }

//    public void sendMessage(BasePushBean bean) {
//        String json = new Gson().toJson(bean);
//        for (WebSocket socket : connections()) {
//            Log.e("MyWebSocketServer", "sendMessageL:" + socket.getRemoteSocketAddress() + ":" + json);
//            socket.send(json);
//        }
//    }
//
//    /**
//     * 连接上以后给tv发送当前设置信息
//     *
//     * @param conn
//     * @param setting
//     */
//    private void sendMessage(WebSocket conn, BasePushBean setting) {
//        String json = new Gson().toJson(setting);
//        WriteLogUtils.write(conn + ":" + json);
//        Log.e("MyWebSocketServer", "sendMessageL:" + conn.getRemoteSocketAddress() + ":" + json);
//        conn.send(json);
//    }

    public void closeConnect() {
        try {
            stop();
            instance = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
