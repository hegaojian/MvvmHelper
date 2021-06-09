package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.ui.viewmodel.TestViewModel
import me.hgj.mvvmhelper.base.BaseVmFragment
import me.hgj.mvvmhelper.ext.logD

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TestFragment3 : BaseVmFragment<TestViewModel>() {

    override val layoutId: Int = R.layout.fragment_test

    override fun initView(savedInstanceState: Bundle?) {
        "TestFragment3-initView".logD("hgj")
    }

    override fun lazyLoadData() {
        "TestFragment3-lazyLoadData".logD("hgj")
    }

    override fun onBindViewClick() {
        super.onBindViewClick()

    }

}