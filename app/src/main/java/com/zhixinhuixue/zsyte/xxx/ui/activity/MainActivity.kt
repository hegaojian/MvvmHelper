package com.zhixinhuixue.zsyte.xxx.ui.activity

import android.os.Bundle
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.app.base.BaseNewActivity
import com.zhixinhuixue.zsyte.xxx.databinding.ActivityMainBinding
import com.zhixinhuixue.zsyte.xxx.ui.adapter.MainAdapter
import com.zhixinhuixue.zsyte.xxx.ui.viewmodel.TestViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/6
 * 描述　:
 */
class MainActivity : BaseNewActivity<TestViewModel, ActivityMainBinding>() {
    override fun initView(savedInstanceState: Bundle?) {
        mToolbar.setCenterTitle(R.string.bottom_title_read)
        mViewBinding.mainViewPager.adapter = MainAdapter(this)
        mViewBinding.mainViewPager.offscreenPageLimit = mViewBinding.mainViewPager.adapter!!.itemCount
        mViewBinding.mainViewPager.isUserInputEnabled = false
        mViewBinding.mainNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationRead -> {
                    mViewBinding.mainViewPager.setCurrentItem(0, false)
                }
                R.id.navigationPaper -> {
                    mViewBinding.mainViewPager.setCurrentItem(1, false)
                }
                R.id.navigationReport -> {
                    mViewBinding.mainViewPager.setCurrentItem(2, false)
                }
                R.id.navigationUser -> {
                    mViewBinding.mainViewPager.setCurrentItem(3, false)
                }
            }
            true
        }
    }

    override fun showToolBar() = false

}
