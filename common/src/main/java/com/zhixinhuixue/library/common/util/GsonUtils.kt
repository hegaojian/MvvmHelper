package com.zhixinhuixue.library.common.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/20
 * 描述　:
 */
object GsonUtils {
    private val gsonBuilder: GsonBuilder by lazy { GsonBuilder() }
    val gson: Gson by lazy { gsonBuilder.create() }


    fun toJson(paramObject: Any?): String = gson.toJson(paramObject)
}