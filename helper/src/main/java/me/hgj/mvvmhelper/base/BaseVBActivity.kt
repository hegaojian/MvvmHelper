package me.hgj.mvvmhelper.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.noober.background.BackgroundLibrary
import java.lang.reflect.ParameterizedType

/**
 * 作者　: hegaojian
 * 时间　: 2021/8/10
 * 描述　:
 */
abstract class BaseVBActivity<VM : BaseViewModel,VB: ViewBinding> : BaseVmActivity<VM>(),BaseIView {

    //使用了 ViewBinding 就不需要 layoutId了，因为 会从 VB 泛型 找到相关的view
    override val layoutId: Int = 0
    lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        initViewBind()
        super.onCreate(savedInstanceState)
    }

    /**
     * 创建 ViewBinding
     */
    private fun initViewBind() {
        //利用反射 根据泛型得到 ViewBinding
        val superClass = javaClass.genericSuperclass
        val aClass = (superClass as ParameterizedType).actualTypeArguments[1] as Class<*>
        BackgroundLibrary.inject(this)
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        mViewBinding =  method.invoke(null,layoutInflater) as VB
        dataBindView = mViewBinding.root
    }
}