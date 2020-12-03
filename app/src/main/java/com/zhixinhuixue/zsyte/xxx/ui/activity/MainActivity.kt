package com.zhixinhuixue.zsyte.xxx.ui.activity

import android.os.Bundle
import com.zhixinhuixue.library.common.base.BaseVmActivity
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.ui.adapter.MainAdapter
import com.zhixinhuixue.zsyte.xxx.viewmodel.TestViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/6
 * 描述　:
 */
class MainActivity(override val layoutId: Int = R.layout.activity_main) : BaseVmActivity<TestViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {
        mToolbar.setCenterTitle(R.string.bottom_title_read)
        mainViewPager.adapter = MainAdapter(this)
        mainViewPager.offscreenPageLimit = mainViewPager.adapter!!.itemCount
        mainViewPager.isUserInputEnabled = false
        mainNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationRead -> {
                    mainViewPager.setCurrentItem(0, false)
                }
                R.id.navigationPaper -> {
                    mainViewPager.setCurrentItem(1, false)
                }
                R.id.navigationReport -> {
                    mainViewPager.setCurrentItem(2, false)
                }
                R.id.navigationUser -> {
                    mainViewPager.setCurrentItem(3, false)
                }
            }
            true
        }
    }

    override fun showToolBar() = false

}
