package com.baidu.android.voicedemo.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.speech.recognizerdemo.R;

/**
 * Created by Jerry on 2017-10-30.
 * 教程 http://blog.csdn.net/chenliqiang12345678/article/details/50504406
 * http://developer.android.com/guide/topics/connectivity/bluetooth-le.html
 */

public class BluetoothActivity extends AppCompatActivity {
    Button openButton, closeButton, searchButton, sendTextButton;
    EditText sendText;

    Context context = this;
    BluetoothAdapter mBluetoothAdapter;

    private static final int REQUEST_ENABLE_BT = 0x1;
    private static final int REQUEST_DISCOVERABLE = 0x2;
    private static final String TAG = "BluetoothActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//全局-隐藏标题栏
        setContentView(R.layout.main_activity);

        openButton = (Button) findViewById(R.id.open_bluetooth);
        closeButton = (Button) findViewById(R.id.close_bluetooth);
        searchButton = (Button) findViewById(R.id.search_bluetooth);
        sendTextButton = (Button) findViewById(R.id.send_command);
        sendText = (EditText)findViewById(R.id.command_text);

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBT();//打开蓝牙
                // 显示所有蓝牙
                // 点击出现连接界面
                // 使第三方硬件可连接蓝牙列表
                // 发送命令的接口-电脑做测试
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeBT();//关闭蓝牙
                // 显示所有蓝牙
                // 点击出现连接界面
                // 使第三方硬件可连接蓝牙列表
                // 发送命令的接口-电脑做测试
            }
        });

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }
    //跳转到不同的activity
//    Intent intent = new Intent();
//    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.setClass(this, JniActivity.class);
//    startActivity(intent);

    //调用BluetoothAdapter进行蓝牙打开操作
    //BluetoothServerSocket
    //bluetooth device操作
    //BluetoothDevice bluetoothDevice = new BluetoothDevice();//获取蓝牙的地址和设备名

    /*Initializes bluetooth adapter
    final BluetoothManager bluetoothManager = (BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
    bluetoothAdapter = bluetoothManager.getAdapter();

    final BluetoothAdapter.LeScanCallback callback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            bluetoothDeviceArrayList.add(device);
            Log.d(TAG, "run: scanning...");
        }
    };*/

    private void openBT() {//打开蓝牙设备
        //检测不到蓝牙开启则尝试开启(弹出申请打开蓝牙提示框)
        if( mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled() ){
            Toast.makeText(context,"正在打开蓝牙...",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "device is not supported bluebooth or wasn't open but I make my best to try it!");
            Intent enableBt = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBt, REQUEST_ENABLE_BT);
            //打开后有提示
            //Intent enableBtCanBeSearch = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //startActivityForResult(enableBtCanBeSearch, REQUEST_DISCOVERABLE);
        }else{
            Toast.makeText(context,"蓝牙已打开",Toast.LENGTH_SHORT).show();
        }
    }

    private void closeBT() {//关闭蓝牙设备
        if (mBluetoothAdapter.getState() == BluetoothAdapter.STATE_ON) {
            mBluetoothAdapter.disable();
            Toast.makeText(context,"正在关闭蓝牙...",Toast.LENGTH_SHORT).show();
        }
    }

    private void ensureDiscoverable() {// 使本机蓝牙可被搜索
        //bluetoothAdapter.startLeScan(callback);
    /*停止扫描
    void stopLeScan(BluetoothAdapter.LeScanCallback callback);*/

        /*/开启蓝牙扫描
        boolean startLeScan(UUID[] serviceUuids, BluetoothAdapter.LeScanCallback callback)；
        boolean startLeScan(BluetoothAdapter.LeScanCallback callback);*/

        if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(
                    BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);//设置被发现时间 在300秒内
            startActivity(discoverableIntent);
        }
    }
}
