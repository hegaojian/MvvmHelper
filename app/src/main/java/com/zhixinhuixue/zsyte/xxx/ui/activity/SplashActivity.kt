package com.zhixinhuixue.zsyte.xxx.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
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
import me.hgj.mvvmhelper.ext.setOnclick
import me.hgj.mvvmhelper.ext.visible
import me.hgj.mvvmhelper.ext.visibleOrGone

/**
 * 作者　: hegaojian
 * 时间　: 2020/12/3
 * 描述　: 启动页
 */
class SplashActivity :BaseActivity<BaseViewModel, ActivitySplashBinding>() {

    //banner数据
    private var resList = arrayOf(R.drawable.sanshang_teacher,R.drawable.yingkongtao_teacher, R.drawable.boduo_teacher)
    //启动页banner
    private lateinit var mViewPager: BannerViewPager<Int>

    override fun initView(savedInstanceState: Bundle?) {
        //启动页设置全屏模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.navigationBarColor = Color.TRANSPARENT
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
        //判断是否第一次进入
        val isFirst = mmkv.getBoolean(ValueKey.isFirst,true)
        if(isFirst){
            mBind.splashImage.gone()
            mViewPager = findViewById(R.id.splash_banner)
            mViewPager.run {
                adapter = SplashBannerAdapter()
                registerLifecycleObserver(lifecycle)
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        //最后一页显示按钮
                        mBind.splashJoin.visibleOrGone(position == resList.lastIndex)
                    }
                })
                create(resList.toList())
            }
        }else{
            mBind.splashImage.visible()
            mBind.splashBanner.gone()
            //设置mBind.splashImage 慢慢变大动画，并监听结束跳转
            mBind.splashImage.animate().scaleX(1.5f).scaleY(1.5f).setDuration(2000).withEndAction {
                jumpToMainActivity()
            }
        }
    }

    /**
     * 点击事件
     */
    override fun onBindViewClick() {
        setOnclick(mBind.splashJoin){
            when(it){
                mBind.splashJoin ->{
                    mmkv.putBoolean(ValueKey.isFirst,false)
                    jumpToMainActivity()
                }
            }
        }
    }

    /**
     * 跳转到主页
     */
    private fun jumpToMainActivity(){
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    /**
     * 是否显示ToolBar
     * @return Boolean
     */
    override fun showToolBar() = false
}