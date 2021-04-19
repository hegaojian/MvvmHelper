package com.zhixinhuixue.library.common.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.multidex.MultiDex
import com.effective.android.anchors.AnchorsManager
import com.effective.android.anchors.Project
import com.zhixinhuixue.library.common.BuildConfig
import com.zhixinhuixue.library.common.core.viewmodel.EventViewModel
import com.zhixinhuixue.library.common.ext.currentProcessName
import com.zhixinhuixue.library.common.util.*

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */

//全局上下文
val appContext: BaseApplication by lazy { BaseApplication.instance }

//界面通信ViewModel
val eventViewModel: EventViewModel by lazy { BaseApplication.eventInstance }

open class BaseApplication : Application(), ViewModelStoreOwner{

    companion object {
        lateinit var instance: BaseApplication
        lateinit var eventInstance: EventViewModel
    }

    private lateinit var mAppViewModelStore: ViewModelStore

    private var mFactory: ViewModelProvider.Factory? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        mAppViewModelStore = ViewModelStore()
        eventInstance = getAppViewModelProvider().get(EventViewModel::class.java)

        val processName = currentProcessName
        if (processName == packageName) {
            // 主进程初始化
            onMainProcessInit()
        } else {
            // 其他进程初始化
            processName?.let { onOtherProcessInit(it) }
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    open fun onMainProcessInit() {
        /**
         * @description  代码的初始化请不要放在onCreate直接操作，按照下面新建异步方法
         */
        AnchorsManager.getInstance()
            .debuggable(BuildConfig.DEBUG)
            //设置锚点
            .addAnchor(InitNetWork.TASK_ID,InitUtils.TASK_ID,InitComm.TASK_ID,InitAppLifecycle.TASK_ID,InitToast.TASK_ID).start(
                Project.Builder("app", AppTaskFactory())
                    .add(InitNetWork.TASK_ID)
                    .add(InitComm.TASK_ID)
                    .add(InitUtils.TASK_ID)
                    .add(InitToast.TASK_ID)
                    .add(InitAppLifecycle.TASK_ID)
                    .build())
    }

    /**
     * 其他进程初始化，[processName] 进程名
     */
    open fun onOtherProcessInit(processName: String) {}


    /**
     * 获取一个全局的ViewModel
     */
    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.getAppFactory())
    }
    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }
    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }
}