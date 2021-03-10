package me.hgj.mvvmhelper.base

import android.view.View
import androidx.fragment.app.Fragment

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
abstract class BaseInitFragment : Fragment() {

    abstract val layoutId: Int

    var dataBindView : View? = null
}