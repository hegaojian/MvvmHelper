package com.zhixinhuixue.zsyte.xxx.app

import android.app.Application
import com.effective.android.anchors.AnchorsManager
import com.effective.android.anchors.Project
import com.zhixinhuixue.zsyte.xxx.BuildConfig
import me.hgj.mvvmhelper.ext.currentProcessName
import me.hgj.mvvmhelper.net.interception.logging.util.LogUtils

/**
 * 作者　: hegaojian
 * 时间　: 2021/6/9
 * 描述　:
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val processName = currentProcessName
        LogUtils.setLog(BuildConfig.DEBUG)
        if (processName == packageName) {
            // 主进程初始化
            onMainProcessInit()
        } else {
            // 其他进程初始化
            processName?.let { onOtherProcessInit(it) }
        }
    }

    private fun onMainProcessInit() {
        /**
         * @description  代码的初始化请不要放在onCreate直接操作，按照下面新建异步方法
         */
        AnchorsManager.getInstance()
            .debuggable(BuildConfig.DEBUG)
            //设置锚点
            .addAnchor(InitNetWork.TASK_ID, InitUtils.TASK_ID, InitComm.TASK_ID, InitToast.TASK_ID).start(
                Project.Builder("app", AppTaskFactory())
                    .add(InitNetWork.TASK_ID)
                    .add(InitComm.TASK_ID)
                    .add(InitUtils.TASK_ID)
                    .add(InitToast.TASK_ID)
                    .build()
            )
    }

    /**
     * 其他进程初始化，[processName] 进程名
     */
    private fun onOtherProcessInit(processName: String) {}

}