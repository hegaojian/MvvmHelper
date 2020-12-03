package com.zhixinhuixue.zsyte.xxx.ui.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhixinhuixue.zsyte.xxx.ui.fragment.FourFragment
import com.zhixinhuixue.zsyte.xxx.ui.fragment.OneFragment
import com.zhixinhuixue.zsyte.xxx.ui.fragment.ThreeFragment
import com.zhixinhuixue.zsyte.xxx.ui.fragment.TwoFragment
import java.util.*

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
        fragments.put(PAGE_ONE, OneFragment())
        fragments.put(PAGE_TWO, TwoFragment())
        fragments.put(PAGE_THREE, ThreeFragment())
        fragments.put(PAGE_FOUR, FourFragment())
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size()
    }
}