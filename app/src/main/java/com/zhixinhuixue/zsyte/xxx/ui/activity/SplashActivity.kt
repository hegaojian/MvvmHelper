package com.zhixinhuixue.zsyte.xxx.ui.activity

import android.os.Bundle
import com.zhixinhuixue.library.common.base.BaseViewModel
import com.zhixinhuixue.library.common.base.BaseVmActivity
import com.zhixinhuixue.library.common.ext.toStartActivity
import com.zhixinhuixue.zsyte.xxx.R

/**
 * 作者　: hegaojian
 * 时间　: 2020/12/3
 * 描述　:
 */
class SplashActivity(override val layoutId: Int = R.layout.activity_splash) :BaseVmActivity<BaseViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {
        jumpToMainActivity()
    }

    private fun jumpToMainActivity(){
        toStartActivity(MainActivity::class.java)
        finish()
    }

    override fun showToolBar() = false
}