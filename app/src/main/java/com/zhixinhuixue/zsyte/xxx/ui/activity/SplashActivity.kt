package com.zhixinhuixue.zsyte.xxx.ui.activity

import android.os.Bundle
import com.zhixinhuixue.zsyte.xxx.app.base.BaseActivity
import com.zhixinhuixue.zsyte.xxx.databinding.ActivitySplashBinding
import me.hgj.mvvmhelper.base.BaseViewModel
import me.hgj.mvvmhelper.ext.toStartActivity

/**
 * 作者　: hegaojian
 * 时间　: 2020/12/3
 * 描述　:
 */
class SplashActivity : BaseActivity<BaseViewModel, ActivitySplashBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        jumpToMainActivity()
    }

    private fun jumpToMainActivity() {
        toStartActivity(MainActivity::class.java)
        finish()
    }

    override fun showToolBar() = false
}