package com.zhixinhuixue.library.common.state

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.zhixinhuixue.library.common.R

/**
 * 作者　: hegaojian
 * 时间　: 2020/12/14
 * 描述　:
 */
class LoadingCallback: Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

    /**
     * 是否是 点击不可重试
     */
    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}