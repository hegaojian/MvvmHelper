package com.zhixinhuixue.library.common.loadmore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.loadmore.BaseLoadMoreView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhixinhuixue.library.common.R
import com.zhixinhuixue.library.common.base.appContext

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:这是baseAdapter的footer布局 暂时废弃，用SmartRefreshLayout 替代
 */
class CustomLoadMoreView : BaseLoadMoreView() {

    override fun getRootView(parent: ViewGroup): View {
        return LayoutInflater.from(appContext).inflate(R.layout.layout_load_more, parent, false)
    }

    /**
     * 加载中
     */
    override fun getLoadingView(holder: BaseViewHolder): View {
        return holder.getView(R.id.load_more_loading_view)
    }

    /**
     * 加载更多
     */
    override fun getLoadComplete(holder: BaseViewHolder): View {
        return holder.getView(R.id.load_more_load_complete_view)
    }

    /**
     * 没有更多数据了
     */
    override fun getLoadEndView(holder: BaseViewHolder): View {
        return holder.getView(R.id.load_more_load_end_view)
    }

    /**
     * 加载失败
     */
    override fun getLoadFailView(holder: BaseViewHolder): View {
        return holder.getView(R.id.load_more_load_fail_view)
    }
}