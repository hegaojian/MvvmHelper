package com.zhixinhuixue.library.common.util

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import java.io.File
import java.io.FileInputStream
import java.util.*

/**
 *  @description:
 *  @author xcl qq:244672784
 *  @Date 2020/7/1
 **/
object StatusBarUtils{

    private const val LIGHT_CONTENT = "light-content"
    private const val DARK_CONTENT = "dark-content"
    private const val TAG = "StatusBarUtils"

    fun darkStyle(activity: Activity, @ColorInt darkColor: Int) {
        setStyle(activity, DARK_CONTENT, darkColor, -1)
    }

    fun lightStyle(activity: Activity, @ColorInt lightColor: Int) {
        setStyle(activity, LIGHT_CONTENT, -1, lightColor)
    }

    private fun setStyle(
        activity: Activity,
        style: String,
        @ColorInt darkColor: Int,
        @ColorInt lightColor: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.decorView.systemUiVisibility =
                if (style == DARK_CONTENT) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0
            if (style == DARK_CONTENT) {
                setColor(activity, darkColor)
                if (isMiUI()) {
                    miUIStatusBar(activity, true)
                }
            } else {
                setColor(activity, lightColor)
            }
        }
    }

    private fun setColor(activity: Activity, id: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.statusBarColor = id
        }
    }


    private  fun isMiUI(): Boolean {
        return Utils.isMiUI
    }

    private fun miUIStatusBar(activity: Activity, dark: Boolean) {
        try {
            val window = activity.window
            val darkModeFlag: Int
            val layoutParams =
                Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field =
                layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            darkModeFlag = field.getInt(layoutParams)
            val method = window.javaClass.getMethod(
                "setExtraFlags",
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )
            if (dark) {
                method.invoke(window, darkModeFlag, darkModeFlag)
            } else {
                method.invoke(window, 0, darkModeFlag)
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }


    private object Utils {
        private const val SYS_EMUI = "sys_emui"
        private const val SYS_MIUI = "sys_miui"
        private const val SYS_FLYME = "sys_flyme"
        private const val KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code"
        private const val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
        private const val KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage"
        private const val KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level"
        private const val KEY_EMUI_VERSION = "ro.build.version.emui"
        private const val KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion"
        val isLollipop_Mr1: Boolean
            get() = Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1

        val isM: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

        val isMiUI: Boolean
            get() = TextUtils.equals(system, SYS_MIUI)

        val system: String?
            get() {
                var SYS: String? = null
                try {
                    val properties = Properties()
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        properties.load(
                            FileInputStream(
                                File(
                                    Environment.getRootDirectory(),
                                    "build.prop"
                                )
                            )
                        )
                        if (properties.getProperty(
                                KEY_MIUI_VERSION_CODE,
                                null
                            ) != null || properties.getProperty(
                                KEY_MIUI_VERSION_NAME,
                                null
                            ) != null || properties.getProperty(
                                KEY_MIUI_INTERNAL_STORAGE,
                                null
                            ) != null
                        ) {
                            SYS = SYS_MIUI
                        } else if (properties.getProperty(
                                KEY_EMUI_API_LEVEL,
                                null
                            ) != null || properties.getProperty(
                                KEY_EMUI_VERSION,
                                null
                            ) != null || properties.getProperty(
                                KEY_EMUI_CONFIG_HW_SYS_VERSION,
                                null
                            ) != null
                        ) {
                            SYS = SYS_EMUI
                        } else if (systemProperty.toLowerCase(Locale.ROOT).contains("flyme")) {
                            SYS = SYS_FLYME
                        }
                    }
                } catch (e: Exception) {
                    Log.i("StatusBar", e.message)
                    e.printStackTrace()
                }
                return SYS
            }

        val systemProperty: String
            get() {
                try {
                    @SuppressLint("PrivateApi") val clz =
                        Class.forName("android.os.SystemProperties")
                    val get = clz.getMethod(
                        "get",
                        String::class.java,
                        String::class.java
                    )
                    return get.invoke(clz, "ro.build.display.id", "") as String
                } catch (ignored: Exception) {
                }
                return ""
            }
    }
}