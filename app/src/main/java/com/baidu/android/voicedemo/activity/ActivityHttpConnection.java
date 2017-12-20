package com.baidu.android.voicedemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.baidu.speech.recognizerdemo.R;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jkCodic
 * on 2017/12/1509 - 28 - Administrator
 * Email : 523090538@qq.com
 *
 * 包含:tcp/udp socket,http连接
 */

public class ActivityHttpConnection  extends AppCompatActivity implements EventListener {
    private Handler mMainHandler;//主线程Handler 用于将从服务器获取的消息显示出来
    private ExecutorService mThreadPool;//线程池 收发数据 开关连接
    Context context = this;
    //Thread socketClientThread,socketServerThread;
    //http://blog.csdn.net/liuyi1207164339/article/details/50960477

    private Socket socket;
    private ServerSocket serverSocket;//用作服务端
    private static final String SERVERIP = "192.168.1.31";
    private static final int CLIENTPORT = 54321;//作为客户端的端口
    private static final int SERVERPORT = 9999;//作为服务端的端口

    InputStream inputStream;
    InputStreamReader isr ;//输入流读取器对象
    BufferedReader br ;
    OutputStream outputStream;
    Button tcpSocketConnect,tcpSocketDisConnect,sendMsgButton,receiveButton;
    TextView showRecv;
    EditText sendText;
    String recvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //延时操作不要放在主线程  注意：注册网络权限；不能在主线程做网络连接
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(R.layout.http_connect);

        tcpSocketConnect = (Button)findViewById(R.id.connect);
        tcpSocketDisConnect = (Button)findViewById(R.id.disConnect);
        receiveButton = (Button)findViewById(R.id.receive);
        sendMsgButton = (Button)findViewById(R.id.sendMessage);
        sendText = (EditText)findViewById(R.id.editText);
        showRecv = (TextView)findViewById(R.id.returnShow);
        tcpSocketDisConnect.setClickable(false);
        receiveButton.setClickable(false);
        sendMsgButton.setClickable(false);

        mThreadPool = Executors.newCachedThreadPool();//初始化线程池
        // 实例化主线程,用于更新接收过来的消息
        mMainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        showRecv.setText(recvMsg);//msg.obj.toString()
                        break;
                }
            }
        };

        tcpSocketConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //建立连接
//                sendMsgButton.setClickable(true); 无作用
//                receiveButton.setClickable(true);
//                tcpSocketDisConnect.setClickable(true);
                // 利用线程池直接开启一个线程 & 执行该线程
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // 创建Socket对象 & 指定服务端的IP 及 端口号
                            socket = new Socket(SERVERIP, CLIENTPORT);
                            //Toast.makeText(context,"连接已建立",Toast.LENGTH_SHORT).show();
                            // 判断客户端和服务器是否连接成功
                            //System.out.println(socket.isConnected());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                //createConnect();//创建连接
                //socketClientThread = new Thread(ActivityHttpConnection.this);
                //socketClientThread.start();
            }
        });

        sendMsgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//发送消息
                // 利用线程池直接开启一个线程 & 执行该线程
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //if(socket.isConnected() != true){//起不了作用
                                //Toast.makeText(context,"信息已发送",Toast.LENGTH_SHORT).show();
                            //}else{
                                // 步骤1：从Socket 获得输出流对象OutputStream
                                // 该对象作用：发送数据
                                outputStream = socket.getOutputStream();
                                // 步骤2：写入需要发送的数据到输出流对象中
                                outputStream.write((sendText.getText().toString()+"\n").getBytes("utf-8"));
                                // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
                                // 步骤3：发送数据到服务端
                                outputStream.flush();
                            //}
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                //sendText();
            }
        });

        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //接收消息
                // 利用线程池直接开启一个线程 & 执行该线程
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //if(socket.isConnected() != true){
                                //Toast.makeText(context,"连接未建立,请重新操作...",Toast.LENGTH_SHORT).show();
                            //}else{
                                // 步骤1：创建输入流对象InputStream
                                inputStream = socket.getInputStream();
                                // 步骤2：创建输入流读取器对象 并传入输入流对象
                                // 该对象作用：获取服务器返回的数据
                                isr = new InputStreamReader(inputStream);
                                br = new BufferedReader(isr);
                                // 步骤3：通过输入流读取器对象 接收服务器发送过来的数据
                                recvMsg = br.readLine();
                                if(recvMsg != null){
                                    sendText.setText(recvMsg);
                                }else{
                                    sendText.setText("receive data error");
                                }
                                // 步骤4:通知主线程,将接收的消息显示到界面
                                Message msg = Message.obtain();
                                msg.what = 0;
                                mMainHandler.sendMessage(msg);
                                //            //读取服务端发来信息
//             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//             recvMsg = reader.readLine();

//            reader.close();
//            //socket.close();//如何保持长连接
                           // }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                //socketServerThread = new Thread(ActivityHttpConnection.this);

                //readMessage()
            }
        });

        tcpSocketDisConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//断开连接
                try {
                    // 断开 客户端发送到服务器 的连接，即关闭输出流对象OutputStream
                    outputStream.close();
                    // 断开 服务器发送到客户端 的连接，即关闭输入流读取器对象BufferedReader
                    br.close();
                    // 最终关闭整个Socket连接
//                    if(socket.isConnected()){
//
//                    }
                    socket.close();
                    //Toast.makeText(context,"连接已断开",Toast.LENGTH_SHORT).show();
                    // 判断客户端和服务器是否已经断开连接
                    System.out.println(socket.isConnected());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //destoryConnect();
            }
        });
    }

//    public void sendText(){
//        mThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    outputStream = socket.getOutputStream();
//                } catch(IOException){
//                    e.printStackTrace();
//                }
//            }
//            });
//        new Thread(){
//            @Override
//            public void run() {
//                sendMsg = sendText.getText().toString();
//                PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
//                writer.println(sendMsg);
//                writer.flush();
//                writer.close();
//            }
//        }.start();
//    }

//    public void createConnect(){
//
//    }

//    public void destoryConnect(){
//
//    }

//    @Override
//    public void run(){
//        //发送一个连接请求
//        try {
//            //serverSocket = new ServerSocket(SERVERPORT);//创建服务端
//            //Socket socket =  serverSocket.accept();  //等待客户端连接

//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {

    }
}
