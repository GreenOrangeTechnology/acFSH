# acFSH 小智智能家居Android
## 使用说明
1.开发环境 AndroidStudio 3.0  , JDK 1.8.0
2.基于百度语音识别bd_speech_sdk_asr_v3.0.3.20170801_60da871  demo
3.下载项目后导入as,真机调试时数据线连接手机

## 版本规划
V1.0.0 1.实现简单的语音识别功能,能够对自定义的语音库和唤醒词有输出回馈
       2.大致UI轮廓(Splash跳转 绿色环保风格 主界面直接显示六大功能模块 开始录音按钮模仿小米语音助手)
       3.蓝牙模块化初始设计
V1.1.0 4.蓝牙连接的实现

## 功能需求说明
语音识别(分支1:解析->相关操作->蓝牙等其它模块->实现控制目的;分支2:合成->音效反馈->实现良好交互作用)
蓝牙控制设计
红外控制设计

## 开发笔记
1.Ctrl+F12可查看类中所有方法和函数
2.10.30 蓝牙控制需求梳理(多客户端连接 延时重连 ) http://blog.csdn.net/qq_24531461/article/details/53466906
http://www.android-doc.com/reference/android/bluetooth/BluetoothAdapter.html
http://www.android-doc.com/guide/topics/connectivity/bluetooth.html
参考小车http://blog.csdn.net/caryee89/article/details/6874557
http://zhouyunan2010.iteye.com/blog/1186021
http://blog.csdn.net/vnanyesheshou/article/details/51554852
3.package包名与类调用关系
4.android生命周期 安卓自定义class如何实现自动生成应用代码
5. extends ListActivity 的使用与限制 http://blog.csdn.net/wangfeijn/article/details/46711337
