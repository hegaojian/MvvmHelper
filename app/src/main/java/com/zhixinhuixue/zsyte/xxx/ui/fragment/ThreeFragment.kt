package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import me.hgj.mvvmhelper.ext.getColorExt
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.app.base.BaseFragment
import com.zhixinhuixue.zsyte.xxx.databinding.FragmentThreeBinding
import com.zhixinhuixue.zsyte.xxx.ui.viewmodel.TestViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class ThreeFragment : BaseFragment<TestViewModel, FragmentThreeBinding>() {

    override val layoutId: Int get() = R.layout.fragment_three

    override fun initView(savedInstanceState: Bundle?) {
        mDataBind.customToolbar.setCenterTitle(R.string.bottom_title_report)
        mDataBind.customToolbar.setBackgroundResource(R.color.colorPrimary_20)
        ImmersionBar.with(this).titleBar(mDataBind.customToolbar).init()
    }

}