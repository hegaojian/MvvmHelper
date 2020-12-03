package com.zhixinhuixue.library.common.util

import android.view.Gravity
import com.chad.library.adapter.base.module.LoadMoreModuleConfig
import com.effective.android.anchors.Project
import com.effective.android.anchors.Task
import com.effective.android.anchors.TaskCreator
import com.hjq.toast.ToastUtils
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV
import com.zhixinhuixue.library.common.BuildConfig
import com.zhixinhuixue.library.common.R
import com.zhixinhuixue.library.common.base.appContext
import com.zhixinhuixue.library.common.ext.getColorExt
import com.zhixinhuixue.library.common.ext.getDimensionExt
import com.zhixinhuixue.library.common.ext.px2dp
import com.zhixinhuixue.library.common.listener.KtxActivityLifecycleCallbacks
import com.zhixinhuixue.library.common.loadmore.CustomLoadMoreView
import com.zhixinhuixue.library.common.net.NetHttpClient
import com.zhixinhuixue.library.net.interception.LogInterceptor
import rxhttp.wrapper.param.RxHttp

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/17
 * 描述　:
 */
object TaskCreator : TaskCreator {
    override fun createTask(taskName: String): Task {
        return when (taskName) {
            InitNetWork.TASK_ID -> InitNetWork()
            InitComm.TASK_ID -> InitComm()
            InitUtils.TASK_ID -> InitUtils()
            InitAppLifecycle.TASK_ID -> InitAppLifecycle()
            else -> InitDefault()
        }
    }
}

class InitDefault : Task(TASK_ID, true) {
    companion object {
        const val TASK_ID = "0"
    }

    override fun run(name: String) {

    }
}

//初始化网络
class InitNetWork : Task(TASK_ID, false) {
    companion object {
        const val TASK_ID = "1"
    }
    override fun run(name: String) {
        //传入自己的OKHttpClient 并添加了自己的拦截器
        RxHttp.init(NetHttpClient.getDefaultOkHttpClient().run {
            addInterceptor(LogInterceptor())//添加Log拦截器
        }.build())
    }
}

//初始化常用控件类
class InitComm : Task(TASK_ID, true) {
    companion object {
        const val TASK_ID = "2"
    }

    override fun run(name: String) {
        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout ->
            //设置 SmartRefreshLayout 通用配置
            layout.setEnableScrollContentWhenLoaded(true)//是否在加载完成时滚动列表显示新的内容
        }
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
            //设置 Head
            ClassicsHeader(context).apply {
                setAccentColor(getColorExt(R.color.colorBlack))
            }
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            //设置 Footer
            ClassicsFooter(context).apply {
                setAccentColor(getColorExt(R.color.colorBlack))
            }
        }
        // 在 Application 中配置全局自定义的 LoadMoreView
        LoadMoreModuleConfig.defLoadMoreView = CustomLoadMoreView()
    }
}

//初始化Utils
class InitUtils : Task(TASK_ID, false) {
    companion object {
        const val TASK_ID = "3"
    }

    override fun run(name: String) {
        //初始化Log打印
        XLog.init(BuildConfig.DEBUG)
        //初始化吐司
        ToastUtils.init(appContext)
        ToastUtils.setGravity(Gravity.BOTTOM, 0, px2dp(getDimensionExt(R.dimen.dp_100)))
        //初始化MMKV
        MMKV.initialize(appContext.filesDir.absolutePath + "/mmkv")
    }
}

//初始化Utils
class InitAppLifecycle : Task(TASK_ID, true) {
    companion object {
        const val TASK_ID = "5"
    }
    override fun run(name: String) {
        //注册全局的Activity生命周期管理
        appContext.registerActivityLifecycleCallbacks(KtxActivityLifecycleCallbacks())
    }
}




class AppTaskFactory : Project.TaskFactory(TaskCreator)