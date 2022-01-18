## Activity生命周期
FirstActivity 跳转到 SecondActivity ，在调回到 FirstActivity 的生命周期为：
```shell
2022-01-18 16:30:11.114 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-11-111]---> FirstActivity onCreate
2022-01-18 16:30:11.124 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-11-122]---> FirstActivity onStart
2022-01-18 16:30:11.129 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-11-126]---> FirstActivity onResume
2022-01-18 16:30:12.111 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-12-108]---> FirstActivity onPause
2022-01-18 16:30:12.168 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-12-165]---> SecondActivity onCreate
2022-01-18 16:30:12.177 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-12-175]---> SecondActivity onStart
2022-01-18 16:30:12.182 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-12-179]---> SecondActivity onResume
2022-01-18 16:30:12.609 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-12-605]---> FirstActivity onStop
2022-01-18 16:30:14.437 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-14-432]---> SecondActivity onPause
2022-01-18 16:30:14.450 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-14-448]---> FirstActivity onRestart
2022-01-18 16:30:14.454 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-14-451]---> FirstActivity onStart
2022-01-18 16:30:14.458 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-14-455]---> FirstActivity onResume
2022-01-18 16:30:14.855 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-14-853]---> SecondActivity onStop
2022-01-18 16:30:14.859 21458-21458/com.wmding.wmdingapplication I/MyLog: [2022-01-18 16-30-14-857]---> SecondActivity onDestroy
```


## 启动方式
Activity 可以设置启动方式，有两种方式进行设置
1、在 AndroidManifest.xml 中通过 android:launchMode="standard" 进行设置，例如：
```xml
<activity android:name=".CommonActivity" android:launchMode="standard"/>
```

2、在 Intent 上设置，flag进行设置，例如：
```java
Intent intent = new Intent(this, SecondActivity.class);
intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
startActivity(intent);
```

可参考：
https://blog.csdn.net/black_bird_cn/article/details/79764794
https://juejin.cn/post/6952886121328345101#heading-3

