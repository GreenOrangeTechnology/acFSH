package com.baidu.android.voicedemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.baidu.speech.EventListener;
import com.baidu.speech.recognizerdemo.R;

/**
 * Created by jkCodic
 * on 2017/12/1509 - 28 - Administrator
 * Email : 523090538@qq.com
 */

public class ActivitySocketConnect extends AppCompatActivity implements EventListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(R.layout.socket_connect);
    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {

    }
}
