# 动画相关

## 属性动画
属性动画中使用：
- ObjectAnimator
- ValueAnimator

## 2、View动画

### 2.1、平移动画

1、在 res/anim 文件夹下新建 translate.xml 文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<translate xmlns:android="http://schemas.android.com/apk/res/android"

        android:duration="5000"
        android:startOffset="1000"
        android:fillBefore="true"
        android:fillAfter="false"
        android:fillEnabled="true"

        android:repeatMode="restart"
        android:repeatCount="3"

        android:fromXDelta="0"
        android:toXDelta="500"
        android:fromYDelta="0"
        android:toYDelta="500">

</translate>
```
公共属性：

- duration:动画时间，单位毫秒
- startOffset：动画延迟执行时间
- fillBefore: 动画播放完后，视图是否会停留在动画开始的状态，默认为true
- fillAfter: 动画播放完后，视图是否会停留在动画结束的状态，优先于fillBefore值，默认为false
- fillEnable： 是否应用fillBefore值，对fillAfter值无影响，默认为true
- restart：选择重复播放动画模式，restart代表正序重放，reverse代表倒序回放，默认为restart
- repeatCount：重放次数（所以动画的播放次数=重放次数+1），为infinite时无限重复，

平移动画的特有属性：

- fromXDelta：视图在水平方向x 移动的起始值
- toXDelta：视图在水平方向x 移动的结束值
- fromYDelta：视图在竖直方向y 移动的起始值
- toYdelta: 视图在竖直方向y 移动的结束值


2、在Activity中进行使用
```
Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
imageView.startAnimation(animation);
```

### 2.2、旋转动画

1、在 res/anim 文件夹下新建 scale.xml 文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<scale xmlns:android="http://schemas.android.com/apk/res/android"

        android:duration="3000"
        android:repeatCount="2"
        android:fillBefore="true"
        android:repeatMode="restart"

        android:fromXScale="0"
        android:toXScale="2"
        android:fromYScale="0"
        android:toYScale="2"
        android:pivotX="50%"
        android:pivotY="50%">


</scale>

```

- fromXScale: 水平方向的起始缩放倍数
- toXScale： 水平方向的结束缩放倍数
- fromYScale： 垂直方向起始缩放倍数
- toYScale： 垂直方向结束缩放倍数
- pivotX：缩放中心点的x坐标
- pivotY： 缩放中心点的y坐标

### 2.3、旋转动画

1、在 res/anim 文件夹下新建 rotate.xml 文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
        android:duration="3000"
        android:repeatCount="2"
        android:repeatMode="restart"
        android:fillBefore="true"

        android:fromDegrees="0"
        android:toDegrees="360"
        android:pivotX="50%"
        android:pivotY="50%">

</rotate>
```

- fromDegrees:旋转起始角度
- toDegrees：旋转结束角度
- pivotX：旋转中心点x轴坐标
- pivotY： 旋转中心点y轴坐标



### 2.4、透明动画

1、在 res/anim 文件夹下新建 alpha.xml 文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<alpha xmlns:android="http://schemas.android.com/apk/res/android"
        android:duration="3000"
        android:startOffset="500"
        android:fillBefore="true"

        android:fromAlpha="1.0"
        android:toAlpha="0.0">

</alpha>
```

- fromAlpha：透明度起始值
- toAlpha： 透明度结束值



### 2.5、组合动画

1、在 res/anim 文件夹下新建 combination.xml 文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"

        android:duration="3000"
        android:fillBefore="true"
        android:repeatCount="0"
        android:repeatMode="restart"
        android:shareInterpolator="true"
        >

    <translate
            android:fromXDelta="0"
            android:toXDelta="250"
            android:fromYDelta="0"
            android:toYDelta="-250"
            android:duration="3000"
            >
    </translate>

    <rotate
            android:pivotX="50%"
            android:pivotY="50%"
            android:duration="3000"
            android:fromDegrees="0"
            android:toDegrees="30"
            >
    </rotate>


    <!--<alpha-->
    <!--android:duration="3000"-->
    <!--android:fromAlpha="1.0"-->
    <!--android:toAlpha="0.5"-->
    <!--/>-->


</set>
```

- set:组合动画的根节点
- shareInterpolator：表示组合动画中的动画是否和集合共享同一个差值器，如果集合不指定插值器，那么子动画需要单独设置

**注意**

- 组合动画播放时是全部动画同时开始，如果想不同动画不同时间开始就要使用android:startOffset属性来延迟单个动画播放时间。
- 在组合动画里scale缩放动画设置的repeatCount（重复播放）和fillBefore（播放完后，视图是否会停留在动画开始的状态）是无效的。所以如果需要重复播放或者回到原位的话需要在set标签里设置。