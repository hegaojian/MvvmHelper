package com.zhixinhuixue.library.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.zhixinhuixue.library.common.R
import com.zhixinhuixue.library.common.ext.*
import com.zhixinhuixue.library.common.state.EmptyCallback
import com.zhixinhuixue.library.common.state.ErrorCallback
import com.zhixinhuixue.library.common.state.LoadingCallback
import com.zhixinhuixue.library.net.entity.base.LoadStatusEntity
import com.zhixinhuixue.library.net.entity.base.LoadingDialogEntity
import com.zhixinhuixue.library.net.entity.loadingtype.LoadingType
import com.zhixinhuixue.library.widget.toolbar.CustomToolBar

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
abstract class BaseVmActivity<VM : BaseViewModel> : BaseActivity(), BaseIView {

    //界面状态管理者
    protected lateinit var uiStatusManger: LoadService<*>

    //当前Activity绑定的 ViewModel
    lateinit var mViewModel: VM

    //toolbar 这个可替换成自己想要的标题栏
    lateinit var mToolbar: CustomToolBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        //生成ViewModel
        mViewModel = createViewModel()
        //初始化 status View
        initStatusView(savedInstanceState)
        //注册界面响应事件
        initLoadingUiChange(mViewModel)
        //初始化绑定observer
        initObserver()
        //初始化请求成功方法
        onRequestSuccess()
        //初始化绑定点击方法
        onBindViewClick()
    }

    private fun initStatusView(savedInstanceState: Bundle?) {
        mToolbar = findViewById(R.id.baseToolBar)
        mToolbar.run {
            setBackgroundColor(getColorExt(R.color.colorBackGround))
            //是否隐藏标题栏
            visibleOrGone(showToolBar())
        }
        initImmersionBar()
        findViewById<FrameLayout>(R.id.baseContentView).addView(if (dataBindView == null) LayoutInflater.from(this).inflate(layoutId, null) else dataBindView)
        uiStatusManger = loadSirCallBackInit().register(if (getLoadingView() == null) findViewById<FrameLayout>(R.id.baseContentView) else getLoadingView()!!){
            onLoadRetry()
        }
        findViewById<FrameLayout>(R.id.baseContentView).post {
            initView(savedInstanceState)
        }
    }

    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 创建观察者
     */
    open fun initObserver() {}

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 是否隐藏 标题栏 默认显示
     */
    open fun showToolBar(): Boolean {
        return true
    }


    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected open fun initImmersionBar() {
        //设置共同沉浸式样式
        if(showToolBar()){
            mToolbar.setBackgroundColor(getColorExt(R.color.colorPrimary))
            setSupportActionBar(mToolbar.getBaseToolBar())
            ImmersionBar.with(this).titleBar(mToolbar).init()
        }
    }

    /**
     * 点击事件方法 配合setOnclick()拓展函数调用，做到黄油刀类似的点击事件
     */
    open fun onBindViewClick() {}

    /**
     * 将传入的ViewModel注册 UI 事件
     * @param viewModel BaseViewModel
     */
    fun initLoadingUiChange(viewModel:BaseViewModel) {
        viewModel.loadingChange.run {
            loading.observeInActivity(this@BaseVmActivity) {
                //在这里接收到请求的loading回调 根据类型 做不同业务
                when(it.loadingType){
                    LoadingType.LOADING_DIALOG ->{
                        //弹窗类型
                        if (it.isShow) {
                            showLoadingExt(it.loadingMessage)
                        } else {
                            dismissLoadingExt()
                        }
                    }
                    LoadingType.LOADING_CUSTOM ->{
                        //自定义 loading类型
                        if (it.isShow) {
                            showCustomLoading(it)
                        } else {
                            dismissCustomLoading(it)
                        }
                    }
                    LoadingType.LOADING_XML ->{
                        // 与界面绑定 空、错误、成功布局的请求类型
                        if (it.isShow) {
                            showLoadingUi()
                        }
                    }
                }
            }
            showEmpty.observeInActivity(this@BaseVmActivity) {
                //分页列表数据请求 为空且第一页 让界面显示 空布局
                onRequestEmpty(it)
            }
            showError.observeInActivity(this@BaseVmActivity) {
                //如果请求错误 并且loading类型为 xml 那么控制界面显示为错误布局
                if (it.loadingType == LoadingType.LOADING_XML) {
                    showErrorUi(it.errorMessage)
                }
                onRequestError(it)
            }
            showSuccess.observeInActivity(this@BaseVmActivity) {
                //如果请求成功 让界面显示 成功布局
                showSuccessUi()
            }
        }
    }

    /**
     * 请求列表数据为空时 回调
     * @param loadStatus LoadStatusEntity
     */
    override fun onRequestEmpty(loadStatus: LoadStatusEntity) {
        showEmptyUi()
    }

    /**
     * 请求接口失败回调，如果界面有请求接口，需要处理错误业务，请实现它 乳沟不实现那么 默认吐司错误消息
     * @param loadStatus LoadStatusEntity
     */
    override fun onRequestError(loadStatus: LoadStatusEntity) {
        loadStatus.toast()
    }

    /**
     * 请求成功的回调放在这里面 没干啥就是取了个名字，到时候好找
     */
    override fun onRequestSuccess() {}

    /**
     * 空界面，错误界面 点击重试时触发的方法，如果有使用 状态布局的话，一般子类都要实现
     */
    override fun onLoadRetry() {}

    /**
     * 显示 成功状态界面
     */
    override fun showSuccessUi() {
        uiStatusManger.showSuccess()
    }

    /**
     * 显示 错误 状态界面
     */
    override fun showErrorUi(errMessage: String) {
        uiStatusManger.showCallback(ErrorCallback::class.java)
    }


    /**
     * 显示 错误 状态界面
     */
    override fun showEmptyUi() {
        uiStatusManger.showCallback(EmptyCallback::class.java)
    }

    /**
     * 显示 loading 状态界面
     */
    override fun showLoadingUi() {
        uiStatusManger.showCallback(LoadingCallback::class.java)
    }

    /**
     * 子类可传入需要被包裹的View，做状态显示-空、错误、加载
     * 如果子类不覆盖该方法 那么会将整个当前Activity界面（除封装的头部Toolbar）都当做View包裹
     */
    open fun getLoadingView(): View? {
        return null
    }

    /**
     * 显示自定义loading 可以根据 setting中的 requestCode 判断是哪个请求
     */
    override fun showCustomLoading(setting: LoadingDialogEntity) {
        showLoadingExt()
    }

    /**
     * 隐藏自定义loading
     */
    override fun dismissCustomLoading(setting:LoadingDialogEntity) {
        dismissLoadingExt()
    }

    /**
     * 初始化 LoadSir 可以传入该界面（Activity/Fragment）专属自定义 CallBack
     * 如果不重写那么会使用 App全局注册的 CallBack 即 AppTaskFactory 中的注册界面状态管理
     * 如果子类重写那么 如下调用  注意：initDefaultCallBackExt 拓展函数里面的注册的CallBack 请跟  AppTaskFactory注册的保持一致
     * return initDefaultCallBackExt()
     *          .addCallback(CustomCallBack())
     *          .addCallback(XXXCallBack()).build()
     * @return LoadSir
     */
    override fun loadSirCallBackInit(): LoadSir {
        return LoadSir.getDefault()
    }
}