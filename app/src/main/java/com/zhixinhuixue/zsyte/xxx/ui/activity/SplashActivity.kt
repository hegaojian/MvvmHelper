package com.zhixinhuixue.zsyte.xxx.ui.activity

import android.os.Bundle
import me.hgj.mvvmhelper.base.BaseViewModel
import me.hgj.mvvmhelper.base.BaseVmActivity
import com.zhixinhuixue.library.common.ext.toStartActivity
import com.zhixinhuixue.zsyte.xxx.R

/**
 * 作者　: hegaojian
 * 时间　: 2020/12/3
 * 描述　:
 */
class SplashActivity(override val layoutId: Int = R.layout.activity_splash) :
    me.hgj.mvvmhelper.base.BaseVmActivity<me.hgj.mvvmhelper.base.BaseViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {
        jumpToMainActivity()
    }

    private fun jumpToMainActivity(){
        toStartActivity(MainActivity::class.java)
        finish()
    }

    override fun showToolBar() = false
}