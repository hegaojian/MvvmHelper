package com.zhixinhuixue.zsyte.xxx.ui.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhixinhuixue.zsyte.xxx.ui.fragment.UserFragment
import com.zhixinhuixue.zsyte.xxx.ui.fragment.HomeFragment

/**
 * 作者　: hegaojian
 * 时间　: 2023/4/3
 * 描述　:
 */
class MainAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    companion object {
        const val PAGE_HOME = 0
        const val PAGE_USER = 1
    }

    private val fragments: SparseArray<Fragment> = SparseArray()

    init {
        fragments.put(PAGE_HOME, HomeFragment.newInstance())
        fragments.put(PAGE_USER, UserFragment.newInstance())
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size()
    }
}