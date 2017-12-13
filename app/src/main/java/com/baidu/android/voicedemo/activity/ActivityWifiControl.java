package com.baidu.android.voicedemo.activity;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.baidu.speech.EventListener;
import com.baidu.speech.recognizerdemo.R;
import com.jerry.bluetooth.MainActivity;

import java.util.List;

/**
 * Created by jkCodi on 2017/12/11.
 */

public class ActivityWifiControl extends AppCompatActivity implements EventListener {
    Context context = this;//上下文对象
    WifiManager.WifiLock wifiLock;//息屏2分钟后自动关,除非锁定

    private List<ScanResult> scanResults;// 扫描出的网络连接列表
    private List<WifiConfiguration> wifiConfigurations;// 网络连接列表
    private WifiManager wifiManager;
    private WifiInfo wifiInfo;
    private static final String TAG = "ActivityWifiControl";
    protected Button open_wifi_button,close_wifi_button,wifi_toggle_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(R.layout.wifi_control);

        wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();//通过wifiManager取得wifiInfo对象

        open_wifi_button = (Button)findViewById(R.id.open_wifi);
        close_wifi_button = (Button)findViewById(R.id.close_wifi);
        wifi_toggle_button = (Button)findViewById(R.id.wifiToggleButton);
        open_wifi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWifi(context);//要将第三方设备与路由器对接

                // 显示所有wifi

                // 点击出现连接界面
                // 使第三方硬件可连接wifi列表
                // 再回来自己连接回路由器
                // 发送命令的接口-电脑做测试
            }
        });
        close_wifi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeWifi(context);
            }
        });
<<<<<<< HEAD
        //wifi_toggle_button.setOnCheckedChangeListener(this);
=======
        //wifi_toggle_button.setOnCheckedChangeListener
>>>>>>> bbff8c2d57b380f595cfc10d9dcb005f98498f9a
                //http://blog.csdn.net/coder_pig/article/details/47035699

        //initView();
        //initPermission();//动态申请权限
//        asr = EventManagerFactory.create(this, "asr");//识别or唤醒?
//        asr.registerListener(this); //  EventListener 中 onEvent方法
//        btn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                start();
//            }
//        });
//        stopBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                stop();
//            }
//        });
//        otherBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startOther();
//            }
//        });
//        if (enableOffline) {
//            loadOfflineEngine(); //测试离线命令词请开启, 测试 ASR_OFFLINE_ENGINE_GRAMMER_FILE_PATH 参数时开启
//        }
    }

    //@Override
//    public void setOnCheckedChangeListener(CompoundButton compoundButton,boolean b){
//        switch (compoundButton.getId()){
//            case R.id.wifi_toggle_button:
//                if(compoundButton.isChecked()) Toast.makeText(this,"打开声音",Toast.LENGTH_SHORT).show();
//                else Toast.makeText(this,"打开声音",Toast.LENGTH_SHORT).show();
//                break;
////            case R.id.swh_status:
////                if(compoundButton.isChecked()) Toast.makeText(this,"开关:ON",Toast.LENGTH_SHORT).show();
////                else Toast.makeText(this,"开关:OFF",Toast.LENGTH_SHORT).show();
////                break;
//
//        }
//    }

    public void openWifi(Context context){
        if(!wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(true);//打开wifi网卡
            Toast.makeText(context,"wifi正在启动...",Toast.LENGTH_SHORT).show();
        }else if(wifiManager.getWifiState() == wifiManager.WIFI_STATE_ENABLING){
            Toast.makeText(context,"wifi还在启动,稍安勿躁...",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"wifi已经启动,不用再开啦...",Toast.LENGTH_SHORT).show();
        }
    }

    public void closeWifi(Context context) {
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);//打开wifi网卡
        } else if (wifiManager.getWifiState() == wifiManager.WIFI_STATE_DISABLED) {
            Toast.makeText(context, "wifi已经关闭,不用再关...", Toast.LENGTH_SHORT).show();
        } else if(wifiManager.getWifiState() == wifiManager.WIFI_STATE_DISABLING){
            Toast.makeText(context, "wifi正在关闭,稍安勿躁...", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "请重新关闭wifi...", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkState(Context context){
        if(wifiManager.getWifiState() == wifiManager.WIFI_STATE_DISABLING){
            Toast.makeText(context,"Wifi正在关闭", Toast.LENGTH_SHORT).show();
        }else if(wifiManager.getWifiState() == wifiManager.WIFI_STATE_DISABLED){
            Toast.makeText(context,"Wifi已经关闭", Toast.LENGTH_SHORT).show();
        }else if(wifiManager.getWifiState() == wifiManager.WIFI_STATE_ENABLING){
            Toast.makeText(context,"Wifi正在开启", Toast.LENGTH_SHORT).show();
        }else if(wifiManager.getWifiState() == wifiManager.WIFI_STATE_ENABLED){
            Toast.makeText(context,"Wifi已经开启", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"没有获取到WiFi状态", Toast.LENGTH_SHORT).show();
        }
    }

    public void acquireWifiLock(){//锁定wifi
        wifiLock.acquire();
    }

    public void releaseWifiLock(){//解锁wifiLock
        //判断是否锁定
        if(wifiLock.isHeld()){
            wifiLock.acquire();
        }
    }

    public void createWifiLock(){//创建一个wifiLock
        wifiLock = wifiManager.createWifiLock("Test");
    }

    //得到配置好的网络
    public List<WifiConfiguration> getConfiguration(){
        return wifiConfigurations;
    }

    public void connectConfiguration(int index){//制定配置好的网络进行连接
        //索引大于配置好的网络索引返回
        if(index > wifiConfigurations.size()){
            return;
        }
        //连接配置好的指定ID的网络
        wifiManager.enableNetwork(wifiConfigurations.get(index).networkId,true);
    }

    public void startScan(Context context){
        wifiManager.startScan();//开始搜索wifi
        scanResults = wifiManager.getScanResults();//得到扫描结果
        wifiConfigurations = wifiManager.getConfiguredNetworks();//的单配置好的网络连接
        if (scanResults == null || scanResults.isEmpty()) return;
        for (ScanResult result : scanResults) {
            Log.v(TAG, "result = " + result.toString());
        }

        //扫描到的结果的构成
        if (wifiConfigurations == null || wifiConfigurations.isEmpty()) return;
        for (WifiConfiguration configuration : wifiConfigurations) {
            Log.v(TAG, "configuration = " + configuration.toString());
        }
        //获取对应到ScanResult的WifiConfiguration, 通常, 此配置对应一个BSSID, 可能是null;
        List<WifiConfiguration> configs = wifiManager.getConfiguredNetworks();//.getMatchingWifiConfig(scanResult);
        //可以打印一下看具体的情况:
        if (configs == null || configs.isEmpty()) return;
        for (WifiConfiguration config : configs) {
            Log.v(TAG, "config = " + config);
        }
        //连接WiFi, 有两个函数:
        //wifiManager.connect(WifiConfiguration config, ActionListener listener); //其中包含两个参数: 第一个是WifiConfiguration, 如果是之前连接过得, 是可以直接连接的;否则需要进行手动进行组合(无密码的除外); 第二个参数是ActionListener, 是WifiManager中的hide的接口, 是连接过程的状态回调; 包含 onSuccess() 和 onFailure(int reason);
        //wifiManager.connect(int networkId, ActionListener listener); //其中包含两个参数: 第一个是只需传入networkId, Android使用这个参数, 对请求连接的WiFi进行识别鉴定;第二个参数同上, 可以传null
    }

    //   EventListener  回调方法
    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
//        String logTxt = "name: " + name;
//        if (params != null && !params.isEmpty()) {
//            logTxt += " ;params :" + params;
//        }
//        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {
//            if (params.contains("\"nlu_result\"")) {
//                if (length > 0 && data.length > 0) {
//                    logTxt += ", 语义解析结果：" + new String(data, offset, length);
//                }
//            }
//        } else if (data != null) {
//            logTxt += " ;data length=" + data.length;
//        }
        //printLog(logTxt);
    }
}
