package me.hgj.mvvmhelper.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import me.hgj.mvvmhelper.R
import me.hgj.mvvmhelper.ext.*
import me.hgj.mvvmhelper.loadsir.callback.Callback
import me.hgj.mvvmhelper.loadsir.callback.SuccessCallback
import me.hgj.mvvmhelper.loadsir.core.LoadService
import me.hgj.mvvmhelper.loadsir.core.LoadSir
import me.hgj.mvvmhelper.net.LoadStatusEntity
import me.hgj.mvvmhelper.net.LoadingDialogEntity
import me.hgj.mvvmhelper.net.LoadingType
import me.hgj.mvvmhelper.widget.state.BaseEmptyCallback
import me.hgj.mvvmhelper.widget.state.BaseErrorCallback
import me.hgj.mvvmhelper.widget.state.BaseLoadingCallback

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
abstract class BaseVmActivity<VM : BaseViewModel> : BaseInitActivity(), BaseIView {

    private var dataBindView: View? = null

    //界面状态管理者
    lateinit var uiStatusManger: LoadService<*>

    //当前Activity绑定的 ViewModel
    lateinit var mViewModel: VM

    //toolbar 这个可替换成自己想要的标题栏
    private var mTitleBarView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        //生成ViewModel
        mViewModel = createViewModel()
        //初始化 status View
        initStatusView(savedInstanceState)
        //注册界面响应事件
        addLoadingUiChange(mViewModel)
        //初始化绑定observer
        initObserver()
        //初始化请求成功方法
        onRequestSuccess()
        //初始化绑定点击方法
        onBindViewClick()
    }

    private fun initStatusView(savedInstanceState: Bundle?) {
        mTitleBarView = getTitleBarView()
        dataBindView = initViewDataBind()
        mTitleBarView?.let {
            findViewById<LinearLayout>(R.id.baseRootView).addView(it, 0)
            //是否隐藏标题栏
            it.visibleOrGone(showToolBar())
        }
        initImmersionBar()
        findViewById<FrameLayout>(R.id.baseContentView).addView(if (dataBindView == null) LayoutInflater.from(this).inflate(layoutId, null) else dataBindView)

        uiStatusManger = if (getEmptyStateLayout() != null || getLoadingStateLayout() != null || getErrorStateLayout() != null) {
            //如果子类有自定义CallBack ，那么就不能用 全局的，得新建一个 LoadSir
            val builder = LoadSir.beginBuilder()
            builder.setEmptyCallBack(getEmptyStateLayout() ?: LoadSir.getDefault().emptyCallBack)
            builder.setLoadingCallBack(getLoadingStateLayout() ?: LoadSir.getDefault().loadingCallBack)
            builder.setErrorCallBack(getErrorStateLayout() ?: LoadSir.getDefault().errorCallBack)
            builder.setDefaultCallback(SuccessCallback::class.java)
            builder.build().register(if (getLoadingView() == null) findViewById<FrameLayout>(R.id.baseContentView) else getLoadingView()!!) {
                onLoadRetry()
            }
        } else {
            //没有自定义CallBack 那么就用全局的LoadSir来注册
            LoadSir.getDefault().register(if (getLoadingView() == null) findViewById<FrameLayout>(R.id.baseContentView) else getLoadingView()!!) {
                onLoadRetry()
            }
        }

        findViewById<FrameLayout>(R.id.baseContentView).post {
            initView(savedInstanceState)
        }
    }

    /**
     * 初始化view 这个方法会有延迟，因为使用了LoadSir，需要等待LoadSir注册完成后才能执行
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 已创建View 执行在 initView 之前，
     * @param savedInstanceState Bundle?
     */
    open fun onCreatedView(savedInstanceState: Bundle?) {

    }

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
        mTitleBarView?.let {
            if (showToolBar()) {
                ImmersionBar.with(this).titleBar(it).init()
            }
        }
    }

    /**
     * 点击事件方法 配合setOnclick()拓展函数调用，做到黄油刀类似的点击事件
     */
    open fun onBindViewClick() {}

    /**
     * 注册 UI 事件 监听请求时的回调UI的操作
     */
    fun addLoadingUiChange(viewModel: BaseViewModel) {
        viewModel.loadingChange.run {
            loading.observe(this@BaseVmActivity) {
                when (it.loadingType) {
                    LoadingType.LOADING_DIALOG -> {
                        if (it.isShow) {
                            showLoading(it)
                        } else {
                            dismissLoading(it)
                        }
                    }
                    LoadingType.LOADING_CUSTOM -> {
                        if (it.isShow) {
                            showCustomLoading(it)
                        } else {
                            dismissCustomLoading(it)
                        }
                    }
                    LoadingType.LOADING_XML -> {
                        if (it.isShow) {
                            showLoadingUi()
                        }
                    }
                    LoadingType.LOADING_NULL -> {
                    }
                }
            }
            showEmpty.observe(this@BaseVmActivity) {
                onRequestEmpty(it)
            }
            showError.observe(this@BaseVmActivity) {
                //如果请求错误 并且loading类型为 xml 那么控制界面显示为错误布局
                if (it.loadingType == LoadingType.LOADING_XML) {
                    showErrorUi(it.errorMessage)
                }
                onRequestError(it)
            }
            showSuccess.observe(this@BaseVmActivity) {
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
        loadStatus.errorMessage.toast()
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
     * @param errMessage String
     */
    override fun showErrorUi(errMessage: String) {
        uiStatusManger.showCallback(
            getErrorStateLayout()?.javaClass ?: LoadSir.getDefault().errorCallBack::class.java
        )
    }

    /**
     * 显示 空数据 状态界面
     */
    override fun showEmptyUi() {
        uiStatusManger.showCallback(
            getEmptyStateLayout()?.javaClass ?: LoadSir.getDefault().emptyCallBack::class.java
        )
    }

    /**
     * 显示 loading 状态界面
     */
    override fun showLoadingUi() {
        uiStatusManger.showCallback(
            getLoadingStateLayout()?.javaClass ?: LoadSir.getDefault().loadingCallBack::class.java
        )
    }

    /**
     * 显示自定义loading 在请求时 设置 loadingType类型为LOADING_CUSTOM 时才有效 可以根据setting中的requestCode判断
     * 具体是哪个请求显示该请求自定义的loading
     * @param setting LoadingDialogEntity
     */
    override fun showCustomLoading(setting: LoadingDialogEntity) {
        showLoadingExt(setting.loadingMessage,setting.coroutineScope)
    }

    /**
     * 隐藏自定义loading 在请求时 设置 loadingType类型为LOADING_CUSTOM 时才有效 可以根据setting中的requestCode判断
     * 具体是哪个请求隐藏该请求自定义的loading
     * @param setting LoadingDialogEntity
     */
    override fun dismissCustomLoading(setting: LoadingDialogEntity) {
        dismissLoadingExt()
    }

    override fun showLoading(setting: LoadingDialogEntity) {
        showLoadingExt(setting.loadingMessage,setting.coroutineScope)
    }

    override fun dismissLoading(setting: LoadingDialogEntity) {
        dismissLoadingExt()
    }

    override fun finish() {
        dismissLoadingExt()
        super.finish()
    }

    /**
     * 供子类BaseVmDbActivity 初始化 DataBinding ViewBinding操作
     */
    open fun initViewDataBind(): View? {
        return null
    }

    override fun getEmptyStateLayout(): Callback? = null
    override fun getErrorStateLayout(): Callback? = null
    override fun getLoadingStateLayout(): Callback? = null

}