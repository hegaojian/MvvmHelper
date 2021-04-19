package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import com.zhixinhuixue.library.common.base.BaseDbFragment
import com.zhixinhuixue.library.common.ext.getColorExt
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.databinding.FragmentThreeBinding
import com.zhixinhuixue.zsyte.xxx.ui.viewmodel.TestViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class ReportFragment : BaseDbFragment<TestViewModel,FragmentThreeBinding>() {

    override val layoutId: Int get() = R.layout.fragment_three

    override fun initView(savedInstanceState: Bundle?) {
        mDataBind.customToolbar.setCenterTitle(R.string.bottom_title_report)
        mDataBind.customToolbar.setBackgroundColor(getColorExt(R.color.colorPrimary_20))
        ImmersionBar.with(this).titleBar(mDataBind.customToolbar).init()
    }

}