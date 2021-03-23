package com.zhixinhuixue.zsyte.xxx.data.repository

import com.zhixinhuixue.library.net.api.NetUrl
import com.zhixinhuixue.library.net.entity.base.ApiPagerResponse
import com.zhixinhuixue.zsyte.xxx.data.response.UserInfo
import rxhttp.IAwait
import rxhttp.toDownload
import rxhttp.wrapper.cahce.CacheMode
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/2
 * 描述　: 数据仓库
 */
object UserRepository {

    /**
     * 登录
     */
    fun login(userName: String, password: String): IAwait<UserInfo> {
        return RxHttp.postForm(NetUrl.LOGIN)
            .add("username", userName)
            .add("password", password)
            .toResponse()
    }

    /**
     * 获取列表信息
     */
    fun getList(pageIndex: Int): IAwait<ApiPagerResponse<Any>> {
        return RxHttp.get(NetUrl.HOME_LIST, pageIndex)
            .toResponse()
    }

}

