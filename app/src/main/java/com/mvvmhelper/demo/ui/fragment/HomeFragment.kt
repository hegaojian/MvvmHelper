package com.mvvmhelper.demo.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.mvvmhelper.demo.R
import com.mvvmhelper.demo.app.base.BaseFragment
import com.mvvmhelper.demo.app.ext.LiveDataEvent
import com.mvvmhelper.demo.databinding.FragmentHomeBinding
import com.mvvmhelper.demo.ui.activity.ListActivity
import com.mvvmhelper.demo.ui.activity.LoginActivity
import com.mvvmhelper.demo.ui.activity.TestActivity
import com.mvvmhelper.demo.ui.viewmodel.TestViewModel
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