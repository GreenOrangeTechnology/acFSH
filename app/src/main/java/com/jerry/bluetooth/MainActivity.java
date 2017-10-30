package com.jerry.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.UUID;

import static com.baidu.speech.core.LogUtil.TAG;

/**
 * Created by Jerry on 2017-10-29.
 * bluetooth manage page-model of smartHome bluetooth controller
 */

public class MainActivity {
    //调用BluetoothAdapter进行蓝牙打开操作
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

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

    private void openBT(){//打开蓝牙设备
        /*检测不到蓝牙开启则尝试开启(弹出申请打开蓝牙提示框)
        if( mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled() ){
            Log.d(TAG, "device is not supported bluebooth or wasn't open but I make my best to try it!");
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }*/
    }

    private void clostBT(){//关闭蓝牙设备
        /*if (mBluetoothAdapter.getState() == BluetoothAdapter.STATE_ON) {
            mBluetoothAdapter.disable();
        }*/
    }

    private void ensureDiscoverable() {// 使本机蓝牙可被搜索
        //bluetoothAdapter.startLeScan(callback);
    /*停止扫描
    void stopLeScan(BluetoothAdapter.LeScanCallback callback);*/

        /*/开启蓝牙扫描
        boolean startLeScan(UUID[] serviceUuids, BluetoothAdapter.LeScanCallback callback)；
        boolean startLeScan(BluetoothAdapter.LeScanCallback callback);

        if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(
                    BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);//设置被发现时间 在300秒内
            startActivity(discoverableIntent);*/
        }
}


