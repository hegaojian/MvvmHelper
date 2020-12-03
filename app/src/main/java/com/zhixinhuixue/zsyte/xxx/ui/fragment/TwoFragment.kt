package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import com.zhixinhuixue.library.common.base.BaseViewModel
import com.zhixinhuixue.library.common.base.BaseVmFragment
import com.zhixinhuixue.library.common.ext.getColorExt
import com.zhixinhuixue.zsyte.xxx.R
import kotlinx.android.synthetic.main.fragment_one.*

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TwoFragment : BaseVmFragment<BaseViewModel>() {

    override val layoutId: Int get() = R.layout.fragment_two


    override fun initView(savedInstanceState: Bundle?) {
        customToolbar.setCenterTitle(R.string.bottom_title_paper)
        customToolbar.setBackgroundColor(getColorExt(R.color.colorRed))
        ImmersionBar.with(this).titleBar(customToolbar).init()
    }


}