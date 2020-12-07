package com.zhixinhuixue.library.net

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/4
 * 描述　: 请求码 作为一个Tag使用 如果请求失败了 就需要他来判断是哪个请求失败了
 */
object RequestMatchCode {
    //默认请求码
    const val DEFAULT = "mmp"
    //登录
    const val LOGIN = "login"
    //首页文章集合
    const val LIST = "list"
    //测试合并并行请求
    const val MERGE = "merge"

}