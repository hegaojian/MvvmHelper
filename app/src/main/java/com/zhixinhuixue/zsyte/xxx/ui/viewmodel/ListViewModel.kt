package com.zhixinhuixue.zsyte.xxx.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhixinhuixue.zsyte.xxx.app.api.NetUrl
import com.zhixinhuixue.zsyte.xxx.data.repository.UserRepository
import com.zhixinhuixue.zsyte.xxx.data.response.ApiPagerResponse
import me.hgj.mvvmhelper.base.BaseViewModel
import me.hgj.mvvmhelper.ext.rxHttpRequest
import me.hgj.mvvmhelper.net.LoadingType

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/4
 * 描述　:
 */
class ListViewModel : BaseViewModel() {

    private var pageIndex = 1

    var listData = MutableLiveData<ApiPagerResponse<Any>>()

    /**
     * 获取列表数据
     * @param isRefresh Boolean 是否是刷新
     * @param loadingXml Boolean 请求时是否需要展示界面加载中loading
     */
    fun getList(isRefresh: Boolean, loadingXml: Boolean = false) {
        if (isRefresh) {
            //是刷新 玩Android的这个接口pageIndex 是0 开始 （真操蛋啊...）
            pageIndex = 0
        }
        rxHttpRequest {
            onRequest = {
                listData.value = UserRepository.getList(pageIndex).await()
                //请求成功 页码+1
                pageIndex++
            }
            loadingType = if (loadingXml) LoadingType.LOADING_XML else LoadingType.LOADING_NULL
            requestCode = NetUrl.HOME_LIST
            isRefreshRequest = isRefresh
        }
    }
}