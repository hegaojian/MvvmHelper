package com.zhixinhuixue.library.common.ext

import com.chad.library.adapter.base.BaseQuickAdapter
import com.hjq.toast.ToastUtils
import com.kingja.loadsir.core.LoadService
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhixinhuixue.library.common.state.ErrorCallback
import com.zhixinhuixue.library.net.entity.base.ApiPagerResponse
import com.zhixinhuixue.library.net.entity.base.LoadStatusEntity

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/5
 * 描述　:
 */


fun SmartRefreshLayout.refresh(refreshAction:()-> Unit = {}):SmartRefreshLayout{
    this.setOnRefreshListener {
        refreshAction.invoke()
    }
    return this
}
fun SmartRefreshLayout.loadMore(loadMoreAction:()-> Unit = {}):SmartRefreshLayout{
    this.setOnLoadMoreListener {
        loadMoreAction.invoke()
    }
    return this
}

/**
 * 列表数据加载成功
 * @receiver BaseQuickAdapter<T,*>
 * @param baseListNetEntity BaseListNetEntity<T>
 */
fun <T> BaseQuickAdapter<T,*>.loadListSuccess(baseListNetEntity: ApiPagerResponse<T>, smartRefreshLayout: SmartRefreshLayout){
    //关闭头部刷新
    smartRefreshLayout.finishRefresh()
    if(baseListNetEntity.isRefresh()){
        //如果是第一页 那么设置最新数据替换
        this.setNewInstance(baseListNetEntity.datas)
    }else{
        //不是第一页，累加数据
        this.addData(baseListNetEntity.datas)
        //刷新一下分割线
        this.recyclerView.invalidateItemDecorations()
    }
    //乳沟还有下一页数据 那么设置 smartRefreshLayout 还可以加载更多数据
    if(baseListNetEntity.hasMore()){
        smartRefreshLayout.finishLoadMore()
    }else{
        //乳沟没有更多数据了，设置 smartRefreshLayout 加载完毕 没有更多数据
        smartRefreshLayout.finishLoadMoreWithNoMoreData()
    }
}

/**
 * 列表数据请求失败
 * @receiver BaseQuickAdapter<*,*>
 * @param loadStatus LoadStatusEntity
 * @param status StateLayout
 */
fun BaseQuickAdapter<*,*>.loadListError(loadStatus: LoadStatusEntity, status:LoadService<*>, smartRefreshLayout: SmartRefreshLayout){
    //关闭头部刷新
    smartRefreshLayout.finishRefresh()
    if (loadStatus.isRefresh && this.data.isEmpty()) {
        //第一次请求，第一页，且没有请求到数据
        status.showCallback(ErrorCallback::class.java)
    } else if (loadStatus.isRefresh) {
        //第一页，但是之前有数据，只给提示
        ToastUtils.show(loadStatus.errorMessage)
    } else {
        // 不是第一页请求，让recyclerview设置加载失败
        smartRefreshLayout.finishLoadMore(false)
        //给个错误提示
        ToastUtils.show(loadStatus.errorMessage)
    }
}