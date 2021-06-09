package me.hgj.mvvmhelper.ext

import androidx.annotation.NonNull
import com.tencent.mmkv.MMKV

/**
 * 获取MMKV
 */
val mmkv: MMKV by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    MMKV.mmkvWithID("mmp")
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
