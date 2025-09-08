package com.mvvmhelper.demo.ui.activity

import android.os.Bundle
import com.mvvmhelper.demo.R
import com.mvvmhelper.demo.app.base.BaseActivity
import com.mvvmhelper.demo.databinding.ActivityMainBinding
import com.mvvmhelper.demo.ui.adapter.MainAdapter
import com.mvvmhelper.demo.ui.viewmodel.TestViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2023/4/3
 * 描述　: 主界面
 */
class MainActivity : BaseActivity<TestViewModel, ActivityMainBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        //设置适配器
        mBind.mainViewPager.adapter = MainAdapter(this)
        //设置缓存页面数量
        mBind.mainViewPager.offscreenPageLimit = mBind.mainViewPager.adapter!!.itemCount
        //禁止滑动
        mBind.mainViewPager.isUserInputEnabled = false
        //设置底部导航栏选择事件
        mBind.mainNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationHome -> {
                    //切换到首页
                    mBind.mainViewPager.setCurrentItem(0, false)
                }
                R.id.navigationUser -> {
                    //切换到个人中心
                    mBind.mainViewPager.setCurrentItem(3, false)
                }
            }
            true
        }
    }

    override fun showToolBar() = false

}
