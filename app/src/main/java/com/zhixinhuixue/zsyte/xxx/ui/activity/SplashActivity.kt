package com.zhixinhuixue.zsyte.xxx.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2
import com.zhixinhuixue.library.common.base.BaseDbActivity
import com.zhixinhuixue.library.common.base.BaseViewModel
import com.zhixinhuixue.library.common.ext.gone
import com.zhixinhuixue.library.common.ext.mmkv
import com.zhixinhuixue.library.common.ext.toStartActivity
import com.zhixinhuixue.library.common.ext.visible
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.databinding.ActivitySplashBinding
import com.zhixinhuixue.zsyte.xxx.ui.adapter.WelcomeBannerViewHolder
import com.zhixinhuixue.zsyte.xxx.util.ValueKey
import com.zhpan.bannerview.BannerViewPager
import me.hgj.jetpackmvvm.demo.app.weight.banner.WelcomeBannerAdapter

/**
 * 作者　: hegaojian
 * 时间　: 2020/12/3
 * 描述　:
 */
class SplashActivity(override val layoutId: Int = R.layout.activity_splash) :BaseDbActivity<BaseViewModel, ActivitySplashBinding>() {

    private var resList = arrayOf(R.drawable.sanshang_teacher,R.drawable.yingkongtao_teacher, R.drawable.boduo_teacher)

    private lateinit var mViewPager: BannerViewPager<Int, WelcomeBannerViewHolder>

    override fun initView(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
        val isFirst = mmkv.getBoolean(ValueKey.isFirst,true)
        if(isFirst){
            mDataBind.splashImage.gone()
            mViewPager = mDataBind.splashBanner as BannerViewPager<Int, WelcomeBannerViewHolder>
            mViewPager.apply {
                adapter = WelcomeBannerAdapter()
                setLifecycleRegistry(lifecycle)
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        if (position == resList.size - 1) {
                            mDataBind.splashJoin.visible()
                        } else {
                            mDataBind.splashJoin.gone()
                        }
                    }
                })
                create(resList.toList())
            }
        }else{
            mDataBind.splashImage.visible()
            mDataBind.splashBanner.gone()
            jumpToMainActivity()
        }
        mDataBind.splashJoin.setOnClickListener {
            mmkv.putBoolean(ValueKey.isFirst,false)
            jumpToMainActivity()
        }
    }

    private fun jumpToMainActivity(){
        toStartActivity(MainActivity::class.java)
        finish()
    }

    override fun showToolBar() = false
}