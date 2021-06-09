package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.app.base.BaseFragment
import com.zhixinhuixue.zsyte.xxx.databinding.FragmentFourBinding
import com.zhixinhuixue.zsyte.xxx.ui.viewmodel.TestViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class FourFragment : BaseFragment<TestViewModel, FragmentFourBinding>() {

    override val layoutId: Int get() = R.layout.fragment_four

    override fun initView(savedInstanceState: Bundle?) {
        //沉浸式
        ImmersionBar.with(this).titleBar(mDataBind.userHeadImg).init()
    }


}