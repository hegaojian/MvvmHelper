package com.zhixinhuixue.zsyte.xxx.ui.fragment

import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.databinding.FragmentOneBinding
import com.zhixinhuixue.zsyte.xxx.ui.activity.TestActivity
import com.zhixinhuixue.zsyte.xxx.ui.viewmodel.TestViewModel
import com.zhixinhuixue.zsyte.xxx.ui.activity.ListActivity
import com.zhixinhuixue.zsyte.xxx.ui.activity.LoginActivity
import me.hgj.mvvmhelper.ext.*

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class OneFragment : me.hgj.mvvmhelper.base.BaseDbFragment<TestViewModel, FragmentOneBinding>() {

    private var downloadApkPath = ""

    override fun initView(savedInstanceState: Bundle?) {
        mDataBind.customToolbar.setCenterTitle(R.string.bottom_title_read)
        mDataBind.customToolbar.setBackgroundResource(R.color.colorPrimary)
        ImmersionBar.with(this).titleBar(mDataBind.customToolbar).init()
    }

    override fun onBindViewClick() {
        setOnclickNoRepeat(mDataBind.loginBtn, mDataBind.testPageBtn,mDataBind.testListBtn,mDataBind.testDownload,mDataBind.testUpload) {
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
                        mDataBind.testUpdateText.text = "下载进度：${it.progress}%"
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
                        mDataBind.testUpdateText.text = "上传进度：${it.progress}%"
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