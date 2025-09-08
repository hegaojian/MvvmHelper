package com.mvvmhelper.demo.ui.viewmodel

import android.net.Uri
import android.os.Build
import androidx.lifecycle.viewModelScope
import com.mvvmhelper.demo.app.api.NetUrl
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import me.hgj.mvvmhelper.base.BaseViewModel
import me.hgj.mvvmhelper.base.appContext
import rxhttp.toFlow
import rxhttp.wrapper.entity.Progress
import rxhttp.wrapper.param.RxHttp
import java.io.File
import java.lang.Exception

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TestViewModel : BaseViewModel() {


    /**
     * 上传
     * @param uploadData Function1<Progress, Unit>
     * @param uploadSuccess Function1<String, Unit>
     * @param uploadError Function1<Throwable, Unit>
     */
    fun upload(filePath: String, uploadData: (Progress<String>) -> Unit = {}, uploadSuccess: (String) -> Unit, uploadError: (Throwable) -> Unit = {}) {
        viewModelScope.launch {
            if (checkedAndroid_Q() && filePath.startsWith("content:")) {
                //android 10 以上
                RxHttp.postForm(NetUrl.UPLOAD_URL)
                    .addPart(appContext, "apkFile", Uri.parse(filePath))
                    .toFlow<String>()
                    .onProgress{
                        //上传进度回调,0-100，仅在进度有更新时才会回调
                        uploadData.invoke(it)
                    }.catch {
                        //异常回调
                        uploadError.invoke(it)
                    }.collect {
                        //成功回调
                        uploadSuccess.invoke(it)
                    }
            } else {
                // android 10以下
                val file = File(filePath)
                if(!file.exists()){
                    uploadError.invoke(Exception("文件不存在"))
                    return@launch
                }
                RxHttp.postForm(NetUrl.UPLOAD_URL)
                    .addFile("apkFile", file)
                    .toFlow<String>()
                    .onProgress {
                        //上传进度回调,0-100，仅在进度有更新时才会回调
                        uploadData.invoke(it)
                    }.catch {
                        //异常回调
                        uploadError.invoke(it)
                    }.collect {
                        //成功回调
                        uploadSuccess.invoke(it)
                    }
            }

        }
    }
}

private fun checkedAndroid_Q(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
}