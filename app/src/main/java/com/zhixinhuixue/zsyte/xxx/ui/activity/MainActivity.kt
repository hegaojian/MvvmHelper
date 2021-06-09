package com.zhixinhuixue.zsyte.xxx.ui.activity

import android.os.Bundle
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.app.base.BaseActivity
import com.zhixinhuixue.zsyte.xxx.databinding.ActivityMainBinding
import com.zhixinhuixue.zsyte.xxx.ui.adapter.MainAdapter
import com.zhixinhuixue.zsyte.xxx.ui.viewmodel.TestViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/6
 * 描述　:
 */
class MainActivity : BaseActivity<TestViewModel, ActivityMainBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mToolbar.setCenterTitle(R.string.bottom_title_read)
        mDataBind.mainViewPager.adapter = MainAdapter(this)
        mDataBind.mainViewPager.offscreenPageLimit = mDataBind.mainViewPager.adapter!!.itemCount
        mDataBind.mainViewPager.isUserInputEnabled = false
        mDataBind.mainNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationRead -> {
                    mDataBind.mainViewPager.setCurrentItem(0, false)
                }
                R.id.navigationPaper -> {
                    mDataBind.mainViewPager.setCurrentItem(1, false)
                }
                R.id.navigationReport -> {
                    mDataBind.mainViewPager.setCurrentItem(2, false)
                }
                R.id.navigationUser -> {
                    mDataBind.mainViewPager.setCurrentItem(3, false)
                }
            }
            true
        }
    }

    override fun showToolBar() = false

}
