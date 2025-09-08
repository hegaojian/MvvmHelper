package com.mvvmhelper.demo.app.ext

import android.app.Activity
import android.content.ContextWrapper
import android.view.View
import com.mvvmhelper.demo.app.widget.CustomToolBar
import me.hgj.mvvmhelper.R
import me.hgj.mvvmhelper.ext.currentActivity


/**
 * 作者　: hegaojian
 * 时间　: 2021/6/9
 * 描述　:
 */

/**
 * 初始化有返回键的toolbar
 */
fun CustomToolBar.initBack(
    titleStr: String = "标题",
    backImg: Int = R.drawable.ic_back,
    onBack: (toolbar: CustomToolBar) -> Unit = {
        it.getActivity()?.finish()
    },
): CustomToolBar {
    this.setCenterTitle(titleStr)
    this.getBaseToolBar().setNavigationIcon(backImg)
    this.getBaseToolBar().setNavigationOnClickListener { onBack.invoke(this) }
    return this
}

fun View.getActivity(): Activity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}