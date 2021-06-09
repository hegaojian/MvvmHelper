package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhixinhuixue.library.common.ext.*
import com.zhixinhuixue.zsyte.xxx.app.api.NetUrl
import com.zhixinhuixue.zsyte.xxx.app.base.BaseFragment
import com.zhixinhuixue.zsyte.xxx.app.widget.SpaceItemDecoration
import com.zhixinhuixue.zsyte.xxx.databinding.ActivityListBinding
import com.zhixinhuixue.zsyte.xxx.ui.adapter.TestAdapter
import com.zhixinhuixue.zsyte.xxx.ui.viewmodel.ListViewModel
import me.hgj.mvvmhelper.ext.loadListError
import me.hgj.mvvmhelper.ext.loadListSuccess
import me.hgj.mvvmhelper.ext.loadMore
import me.hgj.mvvmhelper.ext.refresh
import me.hgj.mvvmhelper.net.LoadStatusEntity

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TestFragment1 : BaseFragment<ListViewModel, ActivityListBinding>() {

    private val testAdapter: TestAdapter by lazy { TestAdapter(arrayListOf()) }

    override fun initView(savedInstanceState: Bundle?) {

        mDataBind.listSmartRefresh.refresh {
            //刷新
            mViewModel.getList(true)
        }.loadMore {
            //加载更多
            mViewModel.getList(false)
        }
        //初始化 recycleView
        mDataBind.listRecyclerView.run {
            layoutManager = LinearLayoutManager(mActivity)
            setHasFixedSize(true)
            addItemDecoration(SpaceItemDecoration(8.dp,8.dp))
            adapter = testAdapter
        }
    }

    /**
     * 懒加载 第一次获取视图的时候 触发
     */
    override fun lazyLoadData() {
        onLoadRetry()
    }

    /**
     * 请求成功
     */
    override fun onRequestSuccess() {
        mViewModel.listData.observe(this, Observer {
            //请求到列表数据
            testAdapter.loadListSuccess(it,mDataBind.listSmartRefresh)
        })
    }

    /**
     * 请求失败
     * @param loadStatus LoadStatusEntity
     */
    override fun onRequestError(loadStatus: LoadStatusEntity) {
        when (loadStatus.requestCode) {
            NetUrl.HOME_LIST -> {
                //列表数据请求失败
                testAdapter.loadListError(loadStatus,mDataBind.listSmartRefresh)
            }
        }
    }

    /**
     * 错误界面 空界面 点击重试
     */
    override fun onLoadRetry() {
        mViewModel.getList(isRefresh = true, loadingXml = true)
    }
}