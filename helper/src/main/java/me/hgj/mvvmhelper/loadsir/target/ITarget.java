package me.hgj.mvvmhelper.loadsir.target;


import me.hgj.mvvmhelper.loadsir.callback.Callback;
import me.hgj.mvvmhelper.loadsir.core.LoadLayout;

/**
 * Description:TODO
 * Create Time:2019/8/29 0029 下午 2:43
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ITarget {
    /**
     *
     * @param target
     * @return
     * v1.3.8
     */
    boolean equals(Object target);
    /**
     * 1.removeView 2.确定LP 3.addView
     * @param target
     * @param onReloadListener
     * @return
     * v1.3.8
     */
    LoadLayout replaceView(Object target, Callback.OnReloadListener onReloadListener);
}
