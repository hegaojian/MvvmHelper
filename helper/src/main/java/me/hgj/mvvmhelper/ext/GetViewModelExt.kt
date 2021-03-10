package com.zhixinhuixue.library.common.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import me.hgj.mvvmhelper.base.BaseApplication
import me.hgj.mvvmhelper.base.BaseViewModel
import me.hgj.mvvmhelper.base.appContext
import java.lang.reflect.ParameterizedType


/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}
fun <DB> getDbClazz(obj: Any): DB {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as DB
}
/**
 * 在Activity中得到Application上下文的ViewModel
 */
inline fun <reified VM : me.hgj.mvvmhelper.base.BaseViewModel> AppCompatActivity.getAppViewModel(): VM {
    (me.hgj.mvvmhelper.base.appContext as? me.hgj.mvvmhelper.base.BaseApplication).let {
        if (it == null) {
            throw NullPointerException("Application没有继承BaseApplication类，暂时无法使用getAppViewModel该方法")
        } else {
            return it.getAppViewModelProvider().get(VM::class.java)
        }
    }
}

/**
 * 在Fragment中得到Application上下文的ViewModel
 * 提示，在fragment中调用该方法时，请在该Fragment onCreate以后调用或者请用by lazy方式懒加载初始化调用，不然会提示requireActivity没有导致错误
 */
inline fun <reified VM : me.hgj.mvvmhelper.base.BaseViewModel> Fragment.getAppViewModel(): VM {
    (me.hgj.mvvmhelper.base.appContext as? me.hgj.mvvmhelper.base.BaseApplication).let {
        if (it == null) {
            throw NullPointerException("你Application没有继承BaseApplication类，暂时无法使用getAppViewModel该方法")
        } else {
            return it.getAppViewModelProvider().get(VM::class.java)
        }
    }
}






