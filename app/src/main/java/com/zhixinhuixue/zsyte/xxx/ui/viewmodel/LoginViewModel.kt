package com.zhixinhuixue.zsyte.xxx.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhixinhuixue.library.common.base.BaseViewModel
import com.zhixinhuixue.library.common.core.databinding.BooleanObservableField
import com.zhixinhuixue.library.common.core.databinding.StringObservableField
import com.zhixinhuixue.library.common.ext.logA
import com.zhixinhuixue.library.common.ext.logD
import com.zhixinhuixue.library.common.ext.rxHttpRequest
import com.zhixinhuixue.library.net.api.NetUrl
import com.zhixinhuixue.library.net.entity.loadingtype.LoadingType
import com.zhixinhuixue.zsyte.xxx.data.repository.UserRepository
import com.zhixinhuixue.zsyte.xxx.data.response.UserInfo
import rxhttp.async
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/3
 * 描述　:
 */
class LoginViewModel : BaseViewModel() {

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
     * 演示一个串 请求 写法
     * @param phoneNumber String
     * @param password String
     */
    fun test1(phoneNumber: String, password: String) {
        rxHttpRequest {
            onRequest = {
                //下面2个接口串行请求，先请求登录接口，根据登录接口返回的数据再调用下一个接口
                val loginData = UserRepository.login(phoneNumber, password).await()
                if(loginData.admin){
                    //如果是 管理员 那么就请求
                    val listData = UserRepository.getList(0).await()
                    //打印一下
                    listData.size.logD()
                }
            }
            loadingType = LoadingType.LOADING_DIALOG
            loadingMessage = "正在登录中....." // 选传
            requestCode = NetUrl.LOGIN // 如果要判断接口错误业务 - 必传
        }
    }

    /**
     * 演示一个并行 请求 写法
     * @param phoneNumber String
     * @param password String
     */
    fun test2(phoneNumber: String, password: String) {
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