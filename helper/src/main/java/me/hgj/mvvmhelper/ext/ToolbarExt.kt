package me.hgj.mvvmhelper.ext

import me.hgj.mvvmhelper.R
import me.hgj.mvvmhelper.widget.toolbar.CustomToolBar

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
/**
 * 初始化有返回键的toolbar
 */
fun CustomToolBar.initBack(
    titleStr: String = "标题",
    backImg: Int = R.drawable.ic_back,
    onBack: (toolbar: CustomToolBar) -> Unit
): CustomToolBar {
    this.setCenterTitle(titleStr)
    this.getBaseToolBar().setNavigationIcon(backImg)
    this.getBaseToolBar().setNavigationOnClickListener { onBack.invoke(this) }
    return this
}



