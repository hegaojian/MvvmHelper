package me.hgj.mvvmhelper.core.livedata

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
sealed class DownloadResultState {

    companion object {

        fun onPending(): DownloadResultState = Pending

        fun onProgress(soFarBytes: Int,totalBytes: Int): DownloadResultState = Progress(soFarBytes,totalBytes)

        fun onSuccess(filePath:String): DownloadResultState = Success(filePath)

        fun onError(errorMsg: String): DownloadResultState = Error(errorMsg)
    }

    object Pending : DownloadResultState()
    data class Progress(val soFarBytes: Int, val totalBytes: Int) : DownloadResultState()
    data class Success(val filePath:String) : DownloadResultState()
    data class Error(val errorMsg: String) : DownloadResultState()
}