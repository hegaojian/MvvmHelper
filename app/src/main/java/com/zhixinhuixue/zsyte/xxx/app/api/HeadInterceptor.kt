package com.zhixinhuixue.zsyte.xxx.app.api

import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

/**
 * 作者　: hegaojian
 * 时间　: 2021/6/9
 * 描述　:
 */
class HeadInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        //模拟了2个公共参数
        builder.addHeader("token", "token123456").build()
        builder.addHeader("device", "Android").build()
        return chain.proceed(builder.build())
    }

}