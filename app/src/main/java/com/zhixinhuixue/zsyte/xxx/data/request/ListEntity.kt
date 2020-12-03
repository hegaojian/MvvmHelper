package com.zhixinhuixue.zsyte.xxx.data.request

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */

data class ListEntity(
    var pageIndex: Int,
    var pageSize: Int,
    var semesterId: String,
    var status: String,
    var subjectId: String,
    var teacherIdList: ArrayList<String>
)