package me.hgj.mvvmhelper.base

import android.app.Application
import android.view.Gravity
import com.hjq.toast.Toaster
import me.hgj.mvvmhelper.ext.dp
import me.hgj.mvvmhelper.ext.isApkInDebug
import me.hgj.mvvmhelper.loadsir.callback.SuccessCallback
import me.hgj.mvvmhelper.loadsir.core.LoadSir
import me.hgj.mvvmhelper.util.KtxActivityLifecycleCallbacks
import me.hgj.mvvmhelper.util.mvvmHelperLog
import me.hgj.mvvmhelper.widget.state.BaseEmptyCallback
import me.hgj.mvvmhelper.widget.state.BaseErrorCallback
import me.hgj.mvvmhelper.widget.state.BaseLoadingCallback

/**
 * 作者　: hegaojian
 * 时间　: 2022/1/13
 * 描述　:
 */

/**
 * 全局上下文，可直接拿
 */
val appContext: Application by lazy { MvvmHelper.app }

object MvvmHelper {

    lateinit var app: Application

    /**
     * 框架初始化
     * @param application Application 全局上下文
     */
    fun init(application: Application) {
        app = application
        mvvmHelperLog = isApkInDebug
        //注册全局 activity生命周期监听
        application.registerActivityLifecycleCallbacks(KtxActivityLifecycleCallbacks())
        LoadSir.beginBuilder()
            .setErrorCallBack(BaseErrorCallback())
            .setEmptyCallBack(BaseEmptyCallback())
            .setLoadingCallBack(BaseLoadingCallback())
            .setDefaultCallback(SuccessCallback::class.java)
            .commit()
        Toaster.init(app)
        Toaster.setGravity(Gravity.BOTTOM, 0, 100.dp)
    }
}