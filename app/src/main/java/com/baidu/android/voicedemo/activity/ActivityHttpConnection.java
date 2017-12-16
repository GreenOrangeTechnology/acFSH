package com.baidu.android.voicedemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.baidu.speech.recognizerdemo.R;

import java.io.*;
import java.net.*;

/**
 * Created by jkCodic
 * on 2017/12/1509 - 28 - Administrator
 * Email : 523090538@qq.com
 *
 * 包含socket连接
 */

public class ActivityHttpConnection  extends AppCompatActivity implements EventListener,Runnable {
    Thread socket_thread;
    private static final String SERVERIP = "192.168.1.151";
    private static final int SERVERPORT = 54321;
    Button tcpSocketConnect,tcpSocketDisConnect;
    TextView returnShow;
    EditText inputAddPort;
    String sendMsg,recvMsg;
    Socket socket = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(R.layout.http_connect);

        tcpSocketConnect = (Button)findViewById(R.id.connect);
        tcpSocketDisConnect = (Button)findViewById(R.id.disConnect);
        returnShow = (TextView)findViewById(R.id.returnShow);
        inputAddPort = (EditText)findViewById(R.id.editText);

        tcpSocketConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socket_thread = new Thread(ActivityHttpConnection.this);
                socket_thread.start();
            }
        });

        tcpSocketDisConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //断开连接
//                try{
//                    client.close();
//                }catch{
//                    e.printStackTrace();
//                }
            }
        });
    }

    @Override
    public void run(){
        //发送一个连接请求

        sendMsg = inputAddPort.getText().toString();
        try {
            socket = new Socket(SERVERIP,SERVERPORT);

            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            writer.println(sendMsg);
            writer.flush();

            //分开处理
            // BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // recvMsg = reader.readLine();
            // if(recvMsg != null){
            //     returnShow.setText(recvMsg);
            // }else{
            //     returnShow.setText("receive data error");
            // }
            writer.close();
            //reader.close();
            socket.close();//如何保持长连接
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {

    }
}
