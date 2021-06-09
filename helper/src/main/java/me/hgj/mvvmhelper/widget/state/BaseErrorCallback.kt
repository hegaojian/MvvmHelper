package me.hgj.mvvmhelper.widget.state

import com.kingja.loadsir.callback.Callback
import me.hgj.mvvmhelper.R

/**
 * 作者　: hegaojian
 * 时间　: 2021/6/8
 * 描述　:
 */
class BaseErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}