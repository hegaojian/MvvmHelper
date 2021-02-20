package com.zhixinhuixue.library.common.ext

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.AnimationType
import com.zhixinhuixue.library.common.util.decoration.DefaultDecoration


/**
 * 纵向recyclerview
 * @receiver RecyclerView
 * @param baseAdapter BaseQuickAdapter<*, *>
 * @return RecyclerView
 */
fun RecyclerView.vertical():RecyclerView{
    layoutManager = LinearLayoutManager(this.context)
    setHasFixedSize(true)
    return this
}

/**
 * 横向 recyclerview
 * @receiver RecyclerView
 * @return RecyclerView
 */
fun RecyclerView.horizontal():RecyclerView{
    layoutManager = LinearLayoutManager(this.context).apply {
        orientation = RecyclerView.HORIZONTAL
    }
    setHasFixedSize(true)
    return this
}

/**
 * grid recyclerview
 * @receiver RecyclerView
 * @return RecyclerView
 */
fun RecyclerView.grid(count:Int):RecyclerView{
    layoutManager = GridLayoutManager(this.context,count)
    setHasFixedSize(true)
    return this
}

fun RecyclerView.divider(block:DefaultDecoration.() ->Unit):RecyclerView{
    val itemDecoration = DefaultDecoration(context).apply(block)
    addItemDecoration(itemDecoration)
    return this
}

