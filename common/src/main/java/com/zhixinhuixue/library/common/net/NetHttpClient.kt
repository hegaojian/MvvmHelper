package com.zhixinhuixue.library.common.net

import com.zhixinhuixue.library.common.base.appContext
import okhttp3.OkHttpClient
import rxhttp.wrapper.cookie.CookieStore
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * 作者　: hegaojian
 * 时间　: 2020/11/3
 * 描述　:
 */
object NetHttpClient {
     fun getDefaultOkHttpClient():  OkHttpClient.Builder {
         //在这里面可以写你想要的配置 太多了，我就简单的写了一点，具体可以看rxHttp的文档，有很多
        return OkHttpClient.Builder()
            .cookieJar(CookieStore(File(appContext.externalCacheDir, "RxHttpCookie")))//使用CookieStore对象磁盘缓存,自动管理cookie 玩安卓自动登录验证
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
    }
}