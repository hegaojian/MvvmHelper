package me.hgj.mvvmhelper.core.viewmodel

import com.kunminx.architecture.ui.callback.UnPeekLiveData
import me.hgj.mvvmhelper.base.BaseViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:专门发送全局的消息
 */
class EventViewModel : BaseViewModel() {

    //登录成功后的通知
    val loginEvent = UnPeekLiveData<Boolean>()
}