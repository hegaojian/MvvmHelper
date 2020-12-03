package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import android.view.View
import com.gyf.immersionbar.ImmersionBar
import com.zhixinhuixue.library.common.base.BaseVmFragment
import com.zhixinhuixue.library.common.ext.*
import com.zhixinhuixue.library.net.error.msg
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.ui.activity.TestActivity
import com.zhixinhuixue.zsyte.xxx.viewmodel.TestViewModel
import com.zhixinhuixue.zsyte.xxx.ui.activity.ListActivity
import com.zhixinhuixue.zsyte.xxx.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_one.*

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class OneFragment(override val layoutId: Int = R.layout.fragment_one) : BaseVmFragment<TestViewModel>() {

    var downloadApkPath = ""

    override fun initView(savedInstanceState: Bundle?) {
        customToolbar.setCenterTitle(R.string.bottom_title_read)
        customToolbar.setBackgroundColor(getColorExt(R.color.colorPrimary))
        ImmersionBar.with(this).titleBar(customToolbar).init()
    }

    override fun onBindViewClick() {
        setOnclickNoRepeat(loginBtn, testPageBtn,testListBtn,testDownload,testUpload) {
            when (it.id) {
                R.id.loginBtn -> {
                    toStartActivity(LoginActivity::class.java)
                }
                R.id.testPageBtn -> {
                    toStartActivity(TestActivity::class.java)
                }
                R.id.testListBtn -> {
                    toStartActivity(ListActivity::class.java)
                }

                R.id.testDownload -> {
                    mViewModel.downLoad({
                        //下载中
                        testUpdateText.text = "下载进度：${it.progress}%"
                    },{
                        //下载完成
                        downloadApkPath = it
                        showDialogMessage("下载成功，路径为：${it}")
                    },{
                        //下载失败
                        showDialogMessage(it.msg)
                    })
                }

                R.id.testUpload -> {
                    mViewModel.upload(downloadApkPath,{
                        //上传中 进度
                       testUpdateText.text = "上传进度：${it.progress}%"
                    },{
                        //上传完成
                        showDialogMessage("上传成功：${it}")
                    },{
                        //上传失败
                        showDialogMessage("${it.msg}--${it.message}")
                    })
                }
            }
        }
    }
}