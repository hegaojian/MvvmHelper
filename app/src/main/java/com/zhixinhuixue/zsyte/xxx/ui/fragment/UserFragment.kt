package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import com.zhixinhuixue.zsyte.xxx.app.base.BaseFragment
import com.zhixinhuixue.zsyte.xxx.databinding.FragmentUserBinding
import com.zhixinhuixue.zsyte.xxx.ui.viewmodel.TestViewModel
import me.hgj.mvvmhelper.ext.immersive

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

    override fun onResume() {
        super.onResume()
        immersive()
    }

}