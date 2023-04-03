package me.hgj.mvvmhelper.ext

import androidx.lifecycle.MutableLiveData


/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */

inline fun <reified T> T?.notNull(notNullAction: (T) -> Unit, nullAction: () -> Unit) {
    if (this != null) {
        notNullAction.invoke(this)
    } else {
        nullAction.invoke()
    }
}

inline var <reified T> MutableLiveData<T>.postValue: T?
    get() {
        return this.value
    }
    set(value) {
        this.postValue(value)
    }

