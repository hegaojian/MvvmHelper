package me.hgj.jetpackmvvm.demo.app.weight.banner

/**
 * 作者　: hegaojian
 * 时间　: 2020/2/20
 * 描述　:
 */

import android.view.View
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.ui.adapter.WelcomeBannerViewHolder
import com.zhpan.bannerview.BaseBannerAdapter

class WelcomeBannerAdapter : BaseBannerAdapter<Int, WelcomeBannerViewHolder>() {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.banner_item_welcome
    }

    override fun createViewHolder(itemView: View, viewType: Int): WelcomeBannerViewHolder {
        return WelcomeBannerViewHolder(itemView)
    }

    override fun onBind(holder: WelcomeBannerViewHolder?,data: Int?,position: Int,pageSize: Int) {
        holder?.bindData(data, position, pageSize)
    }
}
