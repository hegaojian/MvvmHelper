package com.zhixinhuixue.zsyte.xxx.ui.adapter

import com.zhixinhuixue.zsyte.xxx.R
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

/**
 * 作者　: hegaojian
 * 时间　: 2021/7/8
 * 描述　: 闪屏页banner适配器
 */
class SplashBannerAdapter : BaseBannerAdapter<Int>(){
    override fun bindData(holder: BaseViewHolder<Int>?, data: Int?, position: Int, pageSize: Int) {
        holder?:return
        data?:return
        holder.setImageResource(R.id.banner_img,data)
    }

    override fun getLayoutId(viewType: Int) = R.layout.layout_splach_banner
}