package me.hgj.mvvmhelper.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import me.hgj.mvvmhelper.ext.inflateBinding

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
abstract class BaseDbFragment<VM : BaseViewModel,DB: ViewDataBinding> : BaseVmFragment<VM>(),BaseIView {

    //使用了 DataBinding 就不需要 layoutId了，因为 会从 VB 泛型 找到相关的view
    override val layoutId: Int = 0

    private var _binding: DB? = null
    val mBind: DB get() = _binding!!

    /**
     * 创建 DataBinding
     */
    override fun initViewDataBind(inflater: LayoutInflater, container: ViewGroup?): View? {
        _binding = inflateBinding(inflater, container, false)
        _binding?.lifecycleOwner = viewLifecycleOwner
        return mBind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}