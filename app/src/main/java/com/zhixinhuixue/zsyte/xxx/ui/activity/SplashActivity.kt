package com.zhixinhuixue.zsyte.xxx.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2
import com.zhixinhuixue.zsyte.xxx.R
import com.zhixinhuixue.zsyte.xxx.app.base.BaseActivity
import com.zhixinhuixue.zsyte.xxx.app.ext.mmkv
import com.zhixinhuixue.zsyte.xxx.data.annotation.ValueKey
import com.zhixinhuixue.zsyte.xxx.databinding.ActivitySplashBinding
import com.zhixinhuixue.zsyte.xxx.ui.adapter.SplashBannerAdapter
import com.zhpan.bannerview.BannerViewPager
import me.hgj.mvvmhelper.base.BaseViewModel
import me.hgj.mvvmhelper.ext.gone
import me.hgj.mvvmhelper.ext.toStartActivity
import me.hgj.mvvmhelper.ext.visible

/**
 * 作者　: hegaojian
 * 时间　: 2020/12/3
 * 描述　:
 */
class SplashActivity :BaseActivity<BaseViewModel, ActivitySplashBinding>() {

    private var resList = arrayOf(R.drawable.sanshang_teacher,R.drawable.yingkongtao_teacher, R.drawable.boduo_teacher)

    private lateinit var mViewPager: BannerViewPager<Int>

    override fun initView(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
        val isFirst = mmkv.getBoolean(ValueKey.isFirst,true)
        if(isFirst){
            mDataBind.splashImage.gone()
            mViewPager = findViewById(R.id.splash_banner)
            mViewPager.apply {
                adapter = SplashBannerAdapter()
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