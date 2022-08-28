package com.zhixinhuixue.zsyte.xxx.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhixinhuixue.zsyte.xxx.app.api.NetUrl
import com.zhixinhuixue.zsyte.xxx.data.repository.UserRepository
import com.zhixinhuixue.zsyte.xxx.data.response.UserInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import me.hgj.mvvmhelper.base.BaseViewModel
import me.hgj.mvvmhelper.core.databinding.BooleanObservableField
import me.hgj.mvvmhelper.core.databinding.StringObservableField
import me.hgj.mvvmhelper.ext.logA
import me.hgj.mvvmhelper.ext.logI
import me.hgj.mvvmhelper.ext.rxHttpRequest
import me.hgj.mvvmhelper.ext.rxHttpRequestCallBack
import me.hgj.mvvmhelper.net.LoadingType
import rxhttp.async

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/3
 * 描述　:
 */
class LoginViewModel : BaseViewModel() {

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
                loginData.value = UserRepository.login(phoneNumber,password).await()
            }
            loadingType = LoadingType.LOADING_DIALOG //选传 默认为 LoadingType.LOADING_NULL
            loadingMessage = "正在登录中....." // 选传
            requestCode = NetUrl.LOGIN // 选传，如果要判断接口错误业务的话必传
        }
    }

    /**
     * 这个写法可以直接 请求时直接回调
     * @param phoneNumber String
     * @param password String
     * @return MutableLiveData<UserInfo>?
     */
    fun loginCallBack(phoneNumber: String, password: String):MutableLiveData<UserInfo>? {
        return rxHttpRequestCallBack {
            onRequest = {
                iAwaitLiveData?.value = UserRepository.login(phoneNumber,password).await()
            }
            loadingType = LoadingType.LOADING_DIALOG //选传 默认为 LoadingType.LOADING_NULL
            loadingMessage = "正在登录中....." // 选传
            requestCode = NetUrl.LOGIN // 选传，如果要判断接口错误业务的话必传
        }
    }

    /**
     * 演示一个串行 请求 写法
     * @param phoneNumber String
     * @param password String
     */
    fun test1(phoneNumber: String, password: String) {
        rxHttpRequest {
            onRequest = {
                //下面2个接口按顺序请求，先请求 getList接口 请求成功后 再执行 login接口， 其中有任一接口请求失败都会走错误回调
                val listData = UserRepository.getList(0).await()
                "打印一下List的大小${listData.size}".logI()
                val loginData = UserRepository.login(phoneNumber, password).await()
                loginData.username.logI()
                "打印一下用户名${loginData.username}".logI()
            }
            loadingType = LoadingType.LOADING_DIALOG
            loadingMessage = "正在登录中....." // 选传
            requestCode = NetUrl.LOGIN // 如果要判断接口错误业务 - 必传
        }
    }

    /**
     * 演示同时请求多个接口
     * @param phoneNumber String
     * @param password String
     */
    fun test2(phoneNumber: String, password: String) {
        rxHttpRequest {
            onRequest = {
                //下面2个接口同时请求，2个接口都请求成功后 最后合并值。 其中有任一接口请求失败都会走错误回调
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