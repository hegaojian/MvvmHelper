package com.zhixinhuixue.library.widget.statuslayout

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/17
 * 描述　:
 */
enum class State {
    None,
    Loading, // 加载数据
    Content, // 展示数据加载成功
    Empty, // 空布局
    Error // 错误
}