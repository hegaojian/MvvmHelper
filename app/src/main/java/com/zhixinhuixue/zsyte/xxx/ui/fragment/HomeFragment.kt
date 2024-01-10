package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.gyf.immersionbar.ktx.immersionBar
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.app.base.BaseFragment
import com.zhixinhuixue.zsyte.xxx.app.ext.LiveDataEvent
import com.zhixinhuixue.zsyte.xxx.databinding.FragmentHomeBinding
import com.zhixinhuixue.zsyte.xxx.ui.activity.ListActivity
import com.zhixinhuixue.zsyte.xxx.ui.activity.LoginActivity
import com.zhixinhuixue.zsyte.xxx.ui.activity.TestActivity
import com.zhixinhuixue.zsyte.xxx.ui.viewmodel.TestViewModel
import me.hgj.mvvmhelper.ext.*

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class HomeFragment : BaseFragment<TestViewModel, FragmentHomeBinding>() {

    companion object{
        fun newInstance():HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        mBind.customToolbar.setCenterTitle(R.string.bottom_title_home)
        mBind.customToolbar.setBackgroundResource(R.color.colorError)
    }

    override fun onResume() {
        super.onResume()
        mBind.customToolbar.statusPadding()
    }

    override fun onRequestSuccess() {
        //登录成功通知
        LiveDataEvent.loginEvent.observe(viewLifecycleOwner, Observer {
            "登录成功".toast()
        })
    }

    override fun onBindViewClick() {
        setOnclickNoRepeat(mBind.loginBtn, mBind.testPageBtn, mBind.testListBtn) {
            when (it.id) {
                R.id.loginBtn -> {
                    toStartActivity(LoginActivity::class.java)
                }
                R.id.testPageBtn -> {
                    toStartActivity(TestActivity::class.java)
                }
                R.id.testListBtn -> {
                    toStartActivity(ListActivity::class.java)
                }
            }
        }
    }
}