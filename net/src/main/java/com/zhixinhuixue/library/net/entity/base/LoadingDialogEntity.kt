package com.zhixinhuixue.library.net.entity.base

import com.zhixinhuixue.library.net.entity.enum.LoadingType

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/3
 * 描述　:
 */
data class LoadingDialogEntity(
    var loadingType: LoadingType = LoadingType.LOADING_NULL,
    var loadingMessage: String = "",
    var isShow: Boolean = false
)