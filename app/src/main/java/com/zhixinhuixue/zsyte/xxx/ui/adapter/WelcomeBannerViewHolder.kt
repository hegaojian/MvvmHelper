package com.zhixinhuixue.zsyte.xxx.ui.adapter

/**
 * 作者　: hegaojian
 * 时间　: 2021/4/19
 * 描述　:
 */

import android.view.View
import android.widget.ImageView
import com.zhixinhuixue.zsyte.xxx.R
import com.zhpan.bannerview.BaseViewHolder

class WelcomeBannerViewHolder(view: View) : BaseViewHolder<Int>(view) {
    override fun bindData(data: Int?, position: Int, pageSize: Int) {
        val imageView = findView<ImageView>(R.id.banner_img)
        imageView.setImageResource(data ?: R.drawable.sanshang_teacher)
    }
}
