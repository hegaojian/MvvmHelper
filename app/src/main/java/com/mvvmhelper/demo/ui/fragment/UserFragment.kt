package com.mvvmhelper.demo.ui.fragment

import android.os.Bundle
import com.mvvmhelper.demo.app.base.BaseFragment
import com.mvvmhelper.demo.databinding.FragmentUserBinding
import com.mvvmhelper.demo.ui.viewmodel.TestViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2023/4/3
 * 描述　: 个人中心
 */
class UserFragment : BaseFragment<TestViewModel, FragmentUserBinding>() {

    companion object{
        fun newInstance():UserFragment {
            val args = Bundle()
            val fragment = UserFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView(savedInstanceState: Bundle?) {

    }


}