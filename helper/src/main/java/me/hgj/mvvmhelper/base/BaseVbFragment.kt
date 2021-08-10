package me.hgj.mvvmhelper.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
abstract class BaseVbFragment<VM : BaseViewModel, VB : ViewBinding> : BaseVmFragment<VM>(),BaseIView {

    //使用了 ViewBinding 就不需要 layoutId了，因为 会从 VB 泛型 找到相关的view
    override val layoutId: Int = 0

    lateinit var mViewBind: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDataBind(inflater, container)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 创建 ViewBinding
     */
    private fun initDataBind(inflater: LayoutInflater, container: ViewGroup?) {
        //利用反射 根据泛型得到 ViewBinding
        val superClass = javaClass.genericSuperclass
        val aClass = (superClass as ParameterizedType).actualTypeArguments[1] as Class<*>
        val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        mViewBind = method.invoke(null, inflater, container, false) as VB
        //如果重新加载，需要清空之前的view，不然会报错
        (dataBindView?.parent as? ViewGroup)?.removeView(dataBindView)
        dataBindView = mViewBind.root
    }
}