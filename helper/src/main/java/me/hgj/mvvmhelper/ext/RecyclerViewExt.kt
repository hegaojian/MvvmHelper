package me.hgj.mvvmhelper.ext

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.hgj.mvvmhelper.util.decoration.DefaultDecoration


/**
 * 纵向recyclerview
 * @receiver RecyclerView
 * @return RecyclerView
 */
fun RecyclerView.vertical(): RecyclerView {
    layoutManager = LinearLayoutManager(this.context)
    return this
}

/**
 * 横向 recyclerview
 * @receiver RecyclerView
 * @return RecyclerView
 */
fun RecyclerView.horizontal(): RecyclerView {
    layoutManager = LinearLayoutManager(this.context).apply {
        orientation = RecyclerView.HORIZONTAL
    }
    return this
}

/**
 * grid recyclerview
 * @receiver RecyclerView
 * @return RecyclerView
 */
fun RecyclerView.grid(count: Int): RecyclerView {
    layoutManager = GridLayoutManager(this.context, count)
    return this
}

/**
 * 配置万能分割线
 * @receiver RecyclerView
 * @param block [@kotlin.ExtensionFunctionType] Function1<DefaultDecoration, Unit>
 * @return RecyclerView
 */
fun RecyclerView.divider(block: DefaultDecoration.() -> Unit): RecyclerView {
    val itemDecoration = DefaultDecoration(context).apply(block)
    addItemDecoration(itemDecoration)
    return this
}

