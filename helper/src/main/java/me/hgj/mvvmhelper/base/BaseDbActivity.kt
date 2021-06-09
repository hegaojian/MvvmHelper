package me.hgj.mvvmhelper.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import com.noober.background.BackgroundLibrary
import java.lang.reflect.ParameterizedType

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
abstract class BaseDbActivity<VM : BaseViewModel,DB: ViewDataBinding> : BaseVmActivity<VM>(),BaseIView {

    //使用了DataBinding 就不需要 layoutId了，因为 会从DB泛型 找到相关的view
    override val layoutId: Int = 0

    lateinit var mDataBind: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        initDataBind()
        super.onCreate(savedInstanceState)
    }

    /**
     * 创建DataBinding
     */
    private fun initDataBind() {
        //利用反射 根据泛型得到 ViewDataBinding
        val superClass = javaClass.genericSuperclass
        val aClass = (superClass as ParameterizedType).actualTypeArguments[1] as Class<*>
        BackgroundLibrary.inject(this)
        val method = aClass.getDeclaredMethod("inflate",LayoutInflater::class.java)
        mDataBind =  method.invoke(null,layoutInflater) as DB
        dataBindView = mDataBind.root
        mDataBind.lifecycleOwner = this
    }

}