package com.zhixinhuixue.zsyte.xxx.app

import com.effective.android.anchors.Project
import com.effective.android.anchors.Task
import com.effective.android.anchors.TaskCreator
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.app.api.NetHttpClient
import me.hgj.mvvmhelper.base.appContext
import me.hgj.mvvmhelper.ext.getColorExt
import me.hgj.mvvmhelper.loadsir.callback.SuccessCallback
import me.hgj.mvvmhelper.loadsir.core.LoadSir
import me.hgj.mvvmhelper.widget.state.BaseEmptyCallback
import me.hgj.mvvmhelper.widget.state.BaseErrorCallback
import me.hgj.mvvmhelper.widget.state.BaseLoadingCallback
import rxhttp.RxHttpPlugins
import java.util.*

/**
 * 作者　: hegaojian
 * 时间　: 2021/6/9
 * 描述　:
 */

object TaskCreator : TaskCreator {
    override fun createTask(taskName: String): Task {
        return when (taskName) {
            InitNetWork.TASK_ID -> InitNetWork()
            InitComm.TASK_ID -> InitComm()
            InitUtils.TASK_ID -> InitUtils()
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


/**
 * 初始化网络
 */
class InitNetWork : Task(TASK_ID, true) {
    companion object {
        const val TASK_ID = "1"
    }

    override fun run(name: String) {
        //传入自己的OKHttpClient 并添加了自己的拦截器
        RxHttpPlugins.init(NetHttpClient.getDefaultOkHttpClient().build())
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
            layout.setFooterTriggerRate(0.6f)
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
        //注册界面状态管理: 以下代码 其实 在调用MvvmHelper.init()的时候就已经注册了
        //这里写的原因是 框架的 状态布局页面可能不适用于你的项目，你可以添加自己的 错误 空 加载中全局配置
        LoadSir.beginBuilder()
            .setErrorCallBack(BaseErrorCallback())
            .setEmptyCallBack(BaseEmptyCallback())
            .setLoadingCallBack(BaseLoadingCallback())
            .setDefaultCallback(SuccessCallback::class.java)
            .commit()
    }
}

//初始化Utils
class InitUtils : Task(TASK_ID, true) {
    companion object {
        const val TASK_ID = "3"
    }

    override fun run(name: String) {
        //初始化Log打印
        MMKV.initialize(appContext)
    }
}

class AppTaskFactory : Project.TaskFactory(TaskCreator)

/**
 * 模拟初始化SDK
 * @param millis Long
 */
fun doJob(millis: Long) {
    val nowTime = System.currentTimeMillis()
    while (System.currentTimeMillis() < nowTime + millis) {
        //程序阻塞指定时间
        val min = 10
        val max = 99
        val random = Random()
        val num = random.nextInt(max) % (max - min + 1) + min
    }
}