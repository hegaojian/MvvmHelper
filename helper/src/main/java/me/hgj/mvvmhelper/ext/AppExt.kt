package me.hgj.mvvmhelper.ext

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import java.util.*

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/17
 * 描述　:
 */

/**
 * 获取当前进程的名称，默认进程名称是包名
 */
val currentProcessName: String?
    get() {
        val pid = android.os.Process.myPid()
        val mActivityManager = me.hgj.mvvmhelper.base.appContext.getSystemService(
            Context.ACTIVITY_SERVICE
        ) as ActivityManager
        for (appProcess in mActivityManager.runningAppProcesses) {
            if (appProcess.pid == pid) {
                return appProcess.processName
            }
        }
        return null
    }

/**
 * 获取packageName
 */
fun getPackageNameName(context: Context): String {
    try {
        val pi = context.packageManager.getPackageInfo(context.packageName, 0)
        return pi.packageName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return ""
}

/**
 * 获取versionName
 */
fun getAppVersion(context: Context): String {
    try {
        val pi = context.packageManager.getPackageInfo(context.packageName, 0)
        return pi.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return ""
}

private val activityList = LinkedList<Activity>()

//app当前显示的Activity
val currentActivity: Activity? get() = if (activityList.isNullOrEmpty()) null else activityList.last

/**
 * 添加Activity入栈
 */
fun addActivity(activity: Activity) {
    activityList.add(activity)
}

/**
 * 关闭Activity出栈
 */
fun finishActivity(activity: Activity) {
    if (!activity.isFinishing) {
        activity.finish()
    }
    activityList.remove(activity)
}

/**
 * 关闭Activity出栈
 */
fun finishActivity(cls: Class<*>) {
    if (activityList.isNullOrEmpty()) return
    val index = activityList.indexOfFirst { it.javaClass == cls }
    if (index == -1) return
    if (!activityList[index].isFinishing) {
        activityList[index].finish()
    }
    activityList.removeAt(index)
}

/**
 * 关闭所有的Activity 全部出栈
 */
fun finishAllActivity() {
    activityList.forEach {
        if (!it.isFinishing) {
            it.finish()
        }
    }
    activityList.clear()
}

