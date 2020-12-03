package com.zhixinhuixue.library.net.entity.base

import java.io.Serializable

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　: 玩Android 服务器返回的列表数据基类
 */
data class ApiPagerResponse<T>(
    var datas: ArrayList<T>,
    var curPage: Int,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
) : Serializable {
    /**
     * 数据是否为空
     */
    fun isEmpty() = datas.size == 0

    /**
     * 是否为刷新
     */
    fun isRefresh() = offset == 0

    /**
     * 是否还有更多数据
     */
    fun hasMore() = !over

    }



