package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import me.hgj.mvvmhelper.base.BaseVmFragment
import com.zhixinhuixue.library.common.ext.logD
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.viewmodel.TestViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TestFragment3 : me.hgj.mvvmhelper.base.BaseVmFragment<TestViewModel>() {

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