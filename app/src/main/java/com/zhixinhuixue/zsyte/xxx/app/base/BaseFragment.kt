package com.zhixinhuixue.zsyte.xxx.app.base

import androidx.viewbinding.ViewBinding
import me.hgj.mvvmhelper.base.BaseVbFragment
import me.hgj.mvvmhelper.base.BaseViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2021/6/9
 * 描述　: 需要自定义修改什么就重写什么 具体方法可以 搜索 BaseIView 查看
 */
abstract class BaseFragment<VM : BaseViewModel,VB: ViewBinding> : BaseVbFragment<VM, VB>(){

    //需要自定义修改什么就重写什么 具体方法可以 搜索 BaseIView 查看

}