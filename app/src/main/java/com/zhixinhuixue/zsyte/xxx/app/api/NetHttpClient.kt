package com.zhixinhuixue.zsyte.xxx.app.api

import me.hgj.mvvmhelper.base.appContext
import me.hgj.mvvmhelper.net.interception.LogInterceptor
import okhttp3.OkHttpClient
import rxhttp.wrapper.cookie.CookieStore
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * 作者　: hegaojian
 * 时间　: 2021/6/9
 * 描述　:
 */
object NetHttpClient {
    fun getDefaultOkHttpClient():  OkHttpClient.Builder {
        //在这里面可以写你想要的配置 太多了，我就简单的写了一点，具体可以看rxHttp的文档，有很多
        return OkHttpClient.Builder()
            //使用CookieStore对象磁盘缓存,自动管理cookie 玩安卓自动登录验证
            .cookieJar(CookieStore(File(appContext.externalCacheDir, "RxHttpCookie")))
            .connectTimeout(15, TimeUnit.SECONDS)//读取连接超时时间 15秒
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(HeadInterceptor())//自定义头部参数拦截器
            .addInterceptor(LogInterceptor())//添加Log拦截器
    }
}