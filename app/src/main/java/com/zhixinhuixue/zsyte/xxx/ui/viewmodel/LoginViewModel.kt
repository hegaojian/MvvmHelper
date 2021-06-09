package com.zhixinhuixue.zsyte.xxx.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhixinhuixue.zsyte.xxx.app.api.NetUrl
import me.hgj.mvvmhelper.core.databinding.BooleanObservableField
import me.hgj.mvvmhelper.core.databinding.StringObservableField
import com.zhixinhuixue.zsyte.xxx.data.repository.UserRepository
import com.zhixinhuixue.zsyte.xxx.data.response.UserInfo
import me.hgj.mvvmhelper.ext.logA
import me.hgj.mvvmhelper.ext.rxHttpRequest
import me.hgj.mvvmhelper.net.LoadingType
import rxhttp.async
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/3
 * 描述　:
 */
class LoginViewModel : me.hgj.mvvmhelper.base.BaseViewModel() {

    //账户名
    val userName = StringObservableField()

    //密码
    val password = StringObservableField()

    //是否显示明文密码（登录注册界面）
    var isShowPwd = BooleanObservableField()

    //登录请求信息
    val loginData = MutableLiveData<UserInfo>()

    /**
     * 登录
     * @param phoneNumber String
     * @param password String
     */
    fun login(phoneNumber: String, password: String) {
        rxHttpRequest {
            onRequest = {
                loginData.value = RxHttp.postForm(NetUrl.LOGIN)
                    .add("username", phoneNumber)
                    .add("password", password)
                    .toResponse<UserInfo>()
                    .await()
            }
            loadingType = LoadingType.LOADING_DIALOG //选传
            loadingMessage = "正在登录中....." // 选传
            requestCode = NetUrl.LOGIN // 如果要判断接口错误业务 - 必传
        }
    }

    /**
     * 演示一个并行 请求 写法
     * @param phoneNumber String
     * @param password String
     */
    fun test(phoneNumber: String, password: String) {
        rxHttpRequest {
            onRequest = {
                //下面2个接口同时请求，最后合并值 其中有任一接口请求失败都会走错误回调
                val listData = UserRepository.getList(0).async(this)
                val loginData = UserRepository.login(phoneNumber, password).async(this)
                //得到合并值
                val mergeValue = loginData.await().username + listData.await().hasMore()
                //打印一下
                mergeValue.logA()
            }
            loadingType = LoadingType.LOADING_DIALOG
            loadingMessage = "正在登录中....." // 选传
            requestCode = NetUrl.LOGIN // 如果要判断接口错误业务 - 必传
        }
    }

}