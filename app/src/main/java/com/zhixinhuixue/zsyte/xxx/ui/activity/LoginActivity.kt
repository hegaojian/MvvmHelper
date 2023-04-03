package com.zhixinhuixue.zsyte.xxx.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.app.api.NetUrl
import com.zhixinhuixue.zsyte.xxx.app.base.BaseActivity
import com.zhixinhuixue.zsyte.xxx.app.ext.LiveDataEvent
import com.zhixinhuixue.zsyte.xxx.app.ext.initBack
import com.zhixinhuixue.zsyte.xxx.databinding.ActivityLoginBinding
import com.zhixinhuixue.zsyte.xxx.ui.viewmodel.LoginViewModel
import me.hgj.mvvmhelper.ext.*
import me.hgj.mvvmhelper.net.LoadStatusEntity

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        //初始化toolbar
        mToolbar.initBack(getStringExt(R.string.login_submit)) {
            finish()
        }
    }

    /**
     * 请求成功
     */
    override fun onRequestSuccess() {
        //方式1 的 请求成功回调 登录结果
        mViewModel.loginData.observe(this, Observer {
            //请求成功  可以做保存信息等操作 ....
            LiveDataEvent.loginEvent.value = true //通知登录成功
            finish()
        })
    }

    /**
     * 请求失败
     * @param loadStatus LoadStatusEntity
     */
    override fun onRequestError(loadStatus: LoadStatusEntity) {
        // 不重写 这个方法，或者 不注释 super.xxx  默认就会吐司 错误消息，以下代码我们编写自己的处理错误的逻辑
//        super.onRequestError(loadStatus)
        when (loadStatus.requestCode) {
            NetUrl.LOGIN -> {
                //是登录接口 ，弹窗提示错误消息
                showDialogMessage(loadStatus.errorMessage)
            }
        }
    }

    override fun onBindViewClick() {
        //绑定点击事件
        setOnclick(mBind.loginBtn) {
            when (it) {
                mBind.loginBtn -> {
                    when {
                        mBind.loginPhone.isEmpty() -> showDialogMessage("手机号不能为空")
                        mBind.loginPwd.isEmpty() -> showDialogMessage("密码不能为空")
                        else -> {
                            //方式1：
//                            mViewModel.login(mBind.loginPhone.textString(), mBind.loginPwd.textString())
                            //方式2:
                            mViewModel.loginCallBack(mBind.loginPhone.textString(), mBind.loginPwd.textString())?.observe(this){
                                //请求成功  可以做保存信息等操作 ....
                                LiveDataEvent.loginEvent.value = true //通知登录成功
                                finish()
                            }
                        }
                    }
                }
            }
        }
    }

}