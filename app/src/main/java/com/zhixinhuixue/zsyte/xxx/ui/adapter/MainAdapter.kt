package com.zhixinhuixue.zsyte.xxx.ui.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhixinhuixue.zsyte.xxx.ui.fragment.MeFragment
import com.zhixinhuixue.zsyte.xxx.ui.fragment.HomeFragment
import com.zhixinhuixue.zsyte.xxx.ui.fragment.ReportFragment
import com.zhixinhuixue.zsyte.xxx.ui.fragment.GroupFragment

/**
 * @Author: cock
 * @Date: 2020/07/01
 * @Description:
 */
class MainAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    companion object {
        const val PAGE_ONE = 0
        const val PAGE_TWO = 1
        const val PAGE_THREE = 2
        const val PAGE_FOUR = 3
    }

    private val fragments: SparseArray<Fragment> = SparseArray()

    init {
        fragments.put(PAGE_ONE, HomeFragment())
        fragments.put(PAGE_TWO, GroupFragment())
        fragments.put(PAGE_THREE, ReportFragment())
        fragments.put(PAGE_FOUR, MeFragment())
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size()
    }
}