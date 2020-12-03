package com.zhixinhuixue.library.common.listener

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.zhixinhuixue.library.common.ext.addActivity
import com.zhixinhuixue.library.common.ext.removeActivity
import com.zhixinhuixue.library.common.util.XLog

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/17
 * 描述　:
 */
class KtxActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(p0: Activity) {

    }

    override fun onActivityStarted(p0: Activity) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        removeActivity(activity)
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        XLog.d(activity.javaClass.simpleName)
        addActivity(activity)
    }

    override fun onActivityResumed(p0: Activity) {
    }

}