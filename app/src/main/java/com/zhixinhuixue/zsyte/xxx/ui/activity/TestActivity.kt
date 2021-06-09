package com.zhixinhuixue.zsyte.xxx.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.zhixinhuixue.zsyte.xxx.app.base.BaseActivity
import com.zhixinhuixue.zsyte.xxx.app.ext.initBack
import me.hgj.mvvmhelper.base.BaseViewModel
import com.zhixinhuixue.zsyte.xxx.databinding.ActivityTestBinding
import com.zhixinhuixue.zsyte.xxx.ui.fragment.TestFragment1

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TestActivity : BaseActivity<BaseViewModel, ActivityTestBinding>() {

    private val titles = arrayOf("页面1", "页面2", "页面3")
    
    override fun initView(savedInstanceState: Bundle?) {
        mToolbar.initBack("测试Fragment") {
            finish()
        }
        mDataBind.testViewPager.adapter = object :
            FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return TestFragment1()
            }

            override fun getCount(): Int {
                return titles.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return titles[position]
            }
        }
        mDataBind.testTableLayout.setupWithViewPager(mDataBind.testViewPager)
        mDataBind.testViewPager.offscreenPageLimit = titles.size
    }
}