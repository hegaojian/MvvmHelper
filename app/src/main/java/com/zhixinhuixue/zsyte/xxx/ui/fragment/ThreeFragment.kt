package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import com.zhixinhuixue.library.common.base.BaseVmFragment
import com.zhixinhuixue.library.common.ext.getColorExt
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.viewmodel.TestViewModel
import kotlinx.android.synthetic.main.fragment_one.*

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class ThreeFragment : BaseVmFragment<TestViewModel>() {

    override val layoutId: Int get() = R.layout.fragment_three

    override fun initView(savedInstanceState: Bundle?) {
        customToolbar.setCenterTitle(R.string.bottom_title_report)
        customToolbar.setBackgroundColor(getColorExt(R.color.colorPrimary_20))
        ImmersionBar.with(this).titleBar(customToolbar).init()
    }

}