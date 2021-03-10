package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import me.hgj.mvvmhelper.base.BaseDbFragment
import me.hgj.mvvmhelper.base.BaseViewModel
import com.zhixinhuixue.library.common.ext.getColorExt
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.databinding.FragmentTwoBinding

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TwoFragment : me.hgj.mvvmhelper.base.BaseDbFragment<me.hgj.mvvmhelper.base.BaseViewModel, FragmentTwoBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mDataBind.customToolbar.setCenterTitle(R.string.bottom_title_paper)
        mDataBind.customToolbar.setBackgroundColor(getColorExt(R.color.colorRed))
        ImmersionBar.with(this).titleBar(mDataBind.customToolbar).init()
    }

}