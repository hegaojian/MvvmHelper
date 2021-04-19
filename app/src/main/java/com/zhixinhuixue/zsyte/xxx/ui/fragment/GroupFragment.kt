package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import com.zhixinhuixue.library.common.base.BaseDbFragment
import com.zhixinhuixue.library.common.base.BaseViewModel
import com.zhixinhuixue.library.common.ext.getColorExt
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.databinding.FragmentTwoBinding

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class GroupFragment : BaseDbFragment<BaseViewModel,FragmentTwoBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mDataBind.customToolbar.setCenterTitle(R.string.bottom_title_paper)
        mDataBind.customToolbar.setBackgroundColor(getColorExt(R.color.colorRed))
        ImmersionBar.with(this).titleBar(mDataBind.customToolbar).init()
    }

}