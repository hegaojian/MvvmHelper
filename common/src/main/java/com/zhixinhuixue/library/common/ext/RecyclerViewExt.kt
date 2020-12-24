package com.zhixinhuixue.library.common.ext

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.AnimationType


/**
 * 纵向recyclerview
 * @receiver RecyclerView
 * @param baseAdapter BaseQuickAdapter<*, *>
 * @return RecyclerView
 */
fun RecyclerView.initVertical(baseAdapter:BaseQuickAdapter<*,*>):RecyclerView{
    baseAdapter.animationEnable = true
    baseAdapter.setAnimationWithDefault(AnimationType.ScaleIn)
    layoutManager = LinearLayoutManager(this.context)
    setHasFixedSize(true)
    adapter = baseAdapter
    return this
}

/**
 * 横向 recyclerview
 * @receiver RecyclerView
 * @param baseAdapter BaseQuickAdapter<*, *>
 * @return RecyclerView
 */
fun RecyclerView.initHorizontal(baseAdapter:BaseQuickAdapter<*,*>):RecyclerView{
    baseAdapter.animationEnable = true
    baseAdapter.setAnimationWithDefault(AnimationType.ScaleIn)
    layoutManager = LinearLayoutManager(this.context).apply {
        orientation = RecyclerView.HORIZONTAL
    }
    setHasFixedSize(true)
    adapter = baseAdapter
    return this
}