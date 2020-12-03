package com.zhixinhuixue.zsyte.xxx.ui.activity

import android.os.Bundle
import com.zhixinhuixue.library.common.base.BaseViewModel
import com.zhixinhuixue.library.common.base.BaseVmActivity
import com.zhixinhuixue.library.common.ext.toStartActivity
import com.zhixinhuixue.zsyte.xxx.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * 作者　: hegaojian
 * 时间　: 2020/12/3
 * 描述　:
 */
class SplashActivity(override val layoutId: Int = R.layout.activity_splash) :BaseVmActivity<BaseViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {
        // 2秒后直接跳转首页
        splashImage.postDelayed({
            toStartActivity(MainActivity::class.java)
            finish()
        },2000)
    }

    override fun showToolBar() = false
}