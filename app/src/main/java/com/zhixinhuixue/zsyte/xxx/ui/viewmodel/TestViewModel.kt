package com.zhixinhuixue.zsyte.xxx.ui.viewmodel

import android.net.Uri
import android.os.Build
import androidx.lifecycle.rxLifeScope
import com.zhixinhuixue.zsyte.xxx.app.api.NetUrl
import com.zhixinhuixue.zsyte.xxx.app.util.Android10DownloadFactory
import kotlinx.coroutines.Dispatchers
import me.hgj.mvvmhelper.base.BaseViewModel
import me.hgj.mvvmhelper.base.appContext
import rxhttp.toDownload
import rxhttp.wrapper.entity.Progress
import rxhttp.wrapper.entity.ProgressT
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse
import rxhttp.wrapper.param.upload
import java.io.File

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
    fun downLoad(downLoadData:(ProgressT<Any>)->Unit = {}, downLoadSuccess:(String)->Unit, downLoadError:(Throwable)->Unit = {}){
        rxLifeScope.launch({
            if(checkedAndroid_Q()){
                //android 10
                val factory = Android10DownloadFactory(appContext,"${System.currentTimeMillis()}.apk")
                val downLoadPath = RxHttp.get(NetUrl.DOWNLOAD_URL).toDownload(factory,
                    Dispatchers.Main) { progressT: ProgressT<Uri> ->
                    //下载中回调
                    downLoadData.invoke(progressT as ProgressT<Any>)
                }.await()
                //下载完成 回调
                downLoadSuccess.invoke(downLoadPath.toString())
            }else{
                //10以下
                val downLoadPath = RxHttp.get(NetUrl.DOWNLOAD_URL)
                    .toDownload(appContext.externalCacheDir!!.absolutePath+"/${System.currentTimeMillis()}.apk",Dispatchers.Main) {
                        downLoadData.invoke(it as ProgressT<Any>)
                    }
                    .await()
                //下载完成 回调
                downLoadSuccess.invoke(downLoadPath)
            }
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
             if (checkedAndroid_Q() && filePath.startsWith("content:")) {
                 //android 10
                 val result = RxHttp.postForm(NetUrl.UPLOAD_URL)
                     .addPart(appContext, "apkFile", Uri.parse(filePath))
                     .upload(this) {
                         //上传进度回调
                         uploadData.invoke(it)
                     }.toResponse<String>().await()
                 uploadSuccess.invoke(result)
             } else {
                 //10以下
                 val result = RxHttp.postForm(NetUrl.UPLOAD_URL)
                     .addFile("apkFile", File(filePath))
                     .upload(this) {
                         //上传进度回调
                         uploadData.invoke(it)
                     }.toResponse<String>().await()
                 uploadSuccess.invoke(result)
             }

         }, {
             uploadError.invoke(it)
         })
    }

    private fun checkedAndroid_Q(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }
}