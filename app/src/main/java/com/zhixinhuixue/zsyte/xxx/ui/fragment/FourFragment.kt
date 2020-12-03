package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import com.zhixinhuixue.library.common.base.BaseVmFragment
import com.zhixinhuixue.library.common.ext.getColorExt
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.viewmodel.TestViewModel
import kotlinx.android.synthetic.main.fragment_four.*
import kotlinx.android.synthetic.main.fragment_one.*

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class FourFragment : BaseVmFragment<TestViewModel>() {

    override val layoutId: Int get() = R.layout.fragment_four

    override fun initView(savedInstanceState: Bundle?) {
        //沉浸式
        ImmersionBar.with(this).titleBar(userHeadImg).init()
    }


}