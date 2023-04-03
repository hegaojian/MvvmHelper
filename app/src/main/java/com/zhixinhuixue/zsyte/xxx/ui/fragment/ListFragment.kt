package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.app.api.NetUrl
import com.zhixinhuixue.zsyte.xxx.app.base.BaseFragment
import com.zhixinhuixue.zsyte.xxx.databinding.LayoutListBinding
import com.zhixinhuixue.zsyte.xxx.ui.adapter.TestAdapter
import com.zhixinhuixue.zsyte.xxx.ui.viewmodel.ListViewModel
import me.hgj.mvvmhelper.ext.*
import me.hgj.mvvmhelper.net.LoadStatusEntity
import me.hgj.mvvmhelper.util.decoration.DividerOrientation

/**
 * 作者　: hegaojian
 * 时间　: 2023/4/3
 * 描述　:
 */
class ListFragment : BaseFragment<ListViewModel, LayoutListBinding>() {

    private val testAdapter: TestAdapter by lazy { TestAdapter() }

    override fun initView(savedInstanceState: Bundle?) {
        mBind.listSmartRefresh.refresh {
            //刷新
            mViewModel.getList(true)
        }.loadMore {
            //加载更多
            mViewModel.getList(false)
        }
        //初始化 recycleView
        mBind.listRecyclerView.grid(1).divider {
            orientation = DividerOrientation.GRID
            includeVisible = true
            setDivider(10,true)
            setColor(getColorExt(R.color.white))
        }.adapter = testAdapter
    }

    /**
     * 错误界面 空界面 点击重试触发的方法
     */
    override fun onLoadRetry() {
        mViewModel.getList(isRefresh = true, loadingXml = true)
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
            testAdapter.loadListSuccess(it,mBind.listSmartRefresh)
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
                testAdapter.loadListError(loadStatus,mBind.listSmartRefresh)
            }
        }
    }


}