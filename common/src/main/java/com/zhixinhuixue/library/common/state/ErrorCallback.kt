package com.zhixinhuixue.library.common.state

import com.kingja.loadsir.callback.Callback
import com.zhixinhuixue.library.common.R

/**
 * 作者　: hegaojian
 * 时间　: 2020/12/14
 * 描述　:
 */
class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}