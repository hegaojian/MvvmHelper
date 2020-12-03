package com.zhixinhuixue.library.common.ext

import androidx.annotation.NonNull
import com.tencent.mmkv.MMKV
import com.zhixinhuixue.library.common.net.CommonConstant

/**
 * 获取MMKV
 */
val mmkv: MMKV by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    MMKV.mmkvWithID(CommonConstant.MMKV_APP_KEY)
}

/**
 * 根据Key 删除
 */
fun MMKV.remove(@NonNull key: String) {
    mmkv.remove(key)
}

/**
 * 全部删除
 */
fun MMKV.clear() {
    mmkv.clearAll()
}
