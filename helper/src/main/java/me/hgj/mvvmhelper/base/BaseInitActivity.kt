package me.hgj.mvvmhelper.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
abstract class BaseInitActivity : AppCompatActivity() {

    abstract val layoutId: Int

    var dataBindView :View? = null

}