package com.zhixinhuixue.zsyte.xxx.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.zhixinhuixue.library.common.ext.dp
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.app.api.NetUrl
import com.zhixinhuixue.zsyte.xxx.app.base.BaseActivity
import com.zhixinhuixue.zsyte.xxx.app.ext.initBack
import com.zhixinhuixue.zsyte.xxx.databinding.LayoutListBinding
import com.zhixinhuixue.zsyte.xxx.ui.adapter.TestAdapter
import com.zhixinhuixue.zsyte.xxx.ui.viewmodel.ListViewModel
import me.hgj.mvvmhelper.ext.*
import me.hgj.mvvmhelper.net.LoadStatusEntity
import me.hgj.mvvmhelper.util.decoration.DividerOrientation

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/4
 * 描述　:
 */
class ListActivity: BaseActivity<ListViewModel, LayoutListBinding>() {

    private  val testAdapter: TestAdapter by lazy { TestAdapter() }

    override fun initView(savedInstanceState: Bundle?) {
        mToolbar.initBack("列表界面") {
            finish()
        }
        mBind.listSmartRefresh.refresh {
            //下拉刷新
            mViewModel.getList(true)
        }.loadMore {
            //上拉加载
            mViewModel.getList(false)
        }
        //初始化recyclerview
        mBind.listRecyclerView.run {
            //设置 recyclerview 模式 为 网格
            grid(3)
            divider {
                //分割线颜色
                setColor(getColorExt(R.color.white))
                //分割线高度
                setDivider(10.dp)
                //是否首尾都有分割线
                includeVisible = true
                //分割线方向
                orientation = DividerOrientation.GRID
            }
            //设置适配器
            adapter = testAdapter
        }
        //发起请求
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

    /**
     * 错误界面 空界面 点击重试
     */
    override fun onLoadRetry() {
        mViewModel.getList(isRefresh = true, loadingXml = true)
    }
}