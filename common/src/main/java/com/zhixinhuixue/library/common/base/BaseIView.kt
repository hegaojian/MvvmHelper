package com.zhixinhuixue.library.common.base

import com.kingja.loadsir.core.LoadSir
import com.zhixinhuixue.library.net.entity.base.LoadStatusEntity
import com.zhixinhuixue.library.net.entity.base.LoadingDialogEntity

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/4
 * 描述　:
 */
interface BaseIView {

    /**
     * 展示加载中界面
     */
    fun showEmptyUi()

    /**
     * 展示加载中界面
     */
    fun showLoadingUi()

    /**
     * 展示错误界面
     * @param errMessage String
     */
    fun showErrorUi(errMessage: String)

    /**
     * 界面显示加载成功
     */
    fun showSuccessUi()

    /**
     * 请求成功
     */
    fun onRequestSuccess()

    /**
     * 请求失败
     * @param loadStatus LoadStatusEntity
     */
    fun onRequestError(loadStatus: LoadStatusEntity)

    /**
     * 请求数据为空时 在 ResponseParser 中判断了如果是列表数据，是第一页，且没有数据时 回调这个方法
     * @param loadStatus LoadStatusEntity
     */
    fun onRequestEmpty(loadStatus: LoadStatusEntity)

    /**
     * 当界面是错误界面，空界面时，点击触发重试
     */
    fun onLoadRetry()

    /**
     * 显示自定义loading弹窗dialog 可以根据 setting中的 requestCode 判断是哪个请求
     */
    fun showCustomLoading(setting:LoadingDialogEntity)

    /**
     * 隐藏自定义loading弹窗dialog
     */
    fun dismissCustomLoading(setting:LoadingDialogEntity)

    /**
     * 初始化 LoadSir 可以传入该界面（Activity/Fragment）专属自定义 CallBack
     * 如果不重写那么会使用 App全局注册的 CallBack 即 AppTaskFactory 中的注册界面状态管理
     * 如果子类重写那么 这样调用  注意 initDefaultCallBackExt 拓展函数里面的注册的CallBack 请跟  AppTaskFactory注册的保持一致
     * return initDefaultCallBackExt()
     *          .addCallback(CustomCallBack())
     *          .addCallback(XXXCallBack()).build()
     * @return LoadSir
     */
    fun loadSirCallBackInit(): LoadSir

}