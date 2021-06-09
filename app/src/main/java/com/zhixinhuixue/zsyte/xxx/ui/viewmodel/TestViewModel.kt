package com.zhixinhuixue.zsyte.xxx.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.rxLifeScope
import com.zhixinhuixue.zsyte.xxx.app.api.NetUrl
import com.zhixinhuixue.zsyte.xxx.app.util.Android10DownloadFactory
import kotlinx.coroutines.Dispatchers
import me.hgj.mvvmhelper.base.BaseViewModel
import me.hgj.mvvmhelper.base.appContext
import rxhttp.awaitString
import rxhttp.toDownload
import rxhttp.wrapper.entity.Progress
import rxhttp.wrapper.entity.ProgressT
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.upload

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TestViewModel : BaseViewModel() {

    /**
     * 下载
     * @param downLoadData Function1<ProgressT<String>, Unit>
     * @param downLoadSuccess Function1<String, Unit>
     * @param downLoadError Function1<Throwable, Unit>
     */
    fun downLoad(downLoadData:(ProgressT<Uri>)->Unit = {}, downLoadSuccess:(String)->Unit, downLoadError:(Throwable)->Unit = {}){
        rxLifeScope.launch({
            val factory = Android10DownloadFactory(appContext,"${System.currentTimeMillis()}.apk")
            val downLoadPath = RxHttp.get(NetUrl.DOWNLOAD_URL).toDownload(factory,
                Dispatchers.Main) { progressT: ProgressT<Uri> ->
                //下载中回调
                downLoadData.invoke(progressT)
            }.await()
            //下载完成 回调
            downLoadSuccess.invoke(downLoadPath.toString())
        },{
            //下载失败回调
            downLoadError.invoke(it)
        })
    }

    /**
     * 上传
     * @param uploadData Function1<Progress, Unit>
     * @param uploadSuccess Function1<String, Unit>
     * @param uploadError Function1<Throwable, Unit>
     */
    fun upload(filePath:String, uploadData:(Progress)->Unit = {}, uploadSuccess:(String)->Unit, uploadError:(Throwable)->Unit = {}){
        rxLifeScope.launch({
            val result = RxHttp.postForm(NetUrl.UPLOAD_URL)
                .addPart(me.hgj.mvvmhelper.base.appContext, "apkFile", Uri.parse(filePath))
                .upload(this) {
                    //上传进度回调
                    uploadData.invoke(it)
                }.awaitString()
            uploadSuccess.invoke(result)
        }, {
            uploadError.invoke(it)
        })
    }
}