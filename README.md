#### :chicken::chicken::chicken: 这是一个快速开发的框架，使用Kotlin语言开发，MVVM+Jetpack架构，封装了公共头部、界面状态管理、ViewModel、LiveData、DataBinding、ViewBinding、头部刷新、加载更多、沉浸式、全局通知、丰富好用的拓展函数、RxHttp网络请求等等一系列工具

# 本框架不再维护！！！ 推荐使用[JetpackMvvm](https://github.com/hegaojian/JetpackMvvm) ，它是本框架的升级版，升级改动比较小

### 更详细的文档介绍 请看 [Wiki](https://github.com/hegaojian/MvvmHelper/wiki)   [Wiki](https://github.com/hegaojian/MvvmHelper/wiki)   [Wiki](https://github.com/hegaojian/MvvmHelper/wiki)

clone慢的可以用 gitee链接 https://gitee.com/hegaojian/MvvmHelper

## 1.如何集成

- **1.1 在root's build.gradle中加入 Jitpack 仓库 **

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

- **1.2 在app's build.gradle中添加依赖**

```gradle
plugins {
    id 'com.google.devtools.ksp' version '2.2.0-2.0.2'
}

...

dependencies {
  ...
  implementation 'com.github.hegaojian:MvvmHelper:1.2.1'
  implementation 'com.squareup.okhttp3:okhttp:5.1.0' 
  ksp 'com.github.liujingxing.rxhttp:rxhttp-compiler:3.5.0'
}
```

- **1.3 在app's build.gradle中，android 模块下开启DataBinding或者ViewBinding 可以自行选择**

``` gradle
android {
    ...
   buildFeatures {
        dataBinding = true //可选 不用可以不写
        viewBinding = true //可选 不用可以不写
    }
}
 
```

- **1.4 在Application中初始化**

```kotlin
 MvvmHelper.init(this,BuildConfig.DEBUG)
```


## 混淆(项目中自带混淆)


