package com.mvvmhelper.demo.ui.activity

import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.mvvmhelper.demo.app.base.BaseActivity
import com.mvvmhelper.demo.app.ext.initBack
import com.mvvmhelper.demo.databinding.ActivityTestBinding
import com.mvvmhelper.demo.ui.fragment.ListFragment
import me.hgj.mvvmhelper.base.BaseViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TestActivity : BaseActivity<BaseViewModel, ActivityTestBinding>() {

    private val titles = arrayOf("页面1", "页面2", "页面3")

    override fun initView(savedInstanceState: Bundle?) {
        mToolbar.initBack("测试Fragment")
        //设置ViewPager2适配器
        mBind.testViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = titles.size
            override fun createFragment(position: Int) = ListFragment()
        }
        //TabLayoutMediator绑定ViewPager2
        TabLayoutMediator(mBind.testTableLayout, mBind.testViewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
        //设置预加载页面数量
        mBind.testViewPager.offscreenPageLimit = titles.size
    }
}