package com.zhixinhuixue.library.common.ext

import com.kingja.loadsir.core.LoadSir
import com.zhixinhuixue.library.common.base.BaseVmActivity
import com.zhixinhuixue.library.common.base.BaseVmFragment
import com.zhixinhuixue.library.common.state.EmptyCallback
import com.zhixinhuixue.library.common.state.ErrorCallback
import com.zhixinhuixue.library.common.state.LoadingCallback

/**
 * 作者　: hegaojian
 * 时间　: 2021/3/18
 * 描述　:
 */

/**
 * 注册 App全局默认的 CallBack
 * @receiver BaseVmActivity<*>
 * @return LoadSir.Builder
 */
fun BaseVmActivity<*>.initDefaultCallBackExt(): LoadSir.Builder {
    return LoadSir.Builder()
        .addCallback(LoadingCallback())
        .addCallback(EmptyCallback())
        .addCallback(ErrorCallback())
}

/**
 * 注册 App全局默认的 CallBack
 * @receiver BaseVmActivity<*>
 * @return LoadSir.Builder
 */
fun BaseVmFragment<*>.initDefaultCallBackExt(): LoadSir.Builder {
    return LoadSir.Builder()
        .addCallback(LoadingCallback())
        .addCallback(EmptyCallback())
        .addCallback(ErrorCallback())
}