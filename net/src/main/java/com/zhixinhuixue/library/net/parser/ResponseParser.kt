package com.zhixinhuixue.library.net.parser

import com.zhixinhuixue.library.net.NetConstant
import com.zhixinhuixue.library.net.entity.base.ApiPagerResponse
import com.zhixinhuixue.library.net.entity.base.ApiResponse
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.exception.ParseException
import rxhttp.wrapper.parse.AbstractParser
import rxhttp.wrapper.utils.convert
import java.io.IOException
import java.lang.reflect.Type
import kotlin.jvm.Throws

/**
 *
 * 作者　: hegaojian
 * 时间　: 2020/11/2
 * 描述　: 输入T,输出T,并对code统一判断
 */

@Parser(name = "Response")
open class ResponseParser<T> : AbstractParser<T> {
    /**
     * 此构造方法适用于任意Class对象，但更多用于带泛型的Class对象，如：List<Student>
     *
     * 用法:
     * Java: .asParser(new ResponseParser<List<Student>>(){})
     * Kotlin: .asParser(object : ResponseParser<List<Student>>() {})
     *
     * 注：此构造方法一定要用protected关键字修饰，否则调用此构造方法将拿不到泛型类型
     */
    protected constructor() : super()

    /**
     * 此构造方法仅适用于不带泛型的Class对象，如: Student.class
     *
     * 用法
     * Java: .asParser(new ResponseParser<>(Student.class))   或者  .asResponse(Student.class)
     * Kotlin: .asParser(ResponseParser(Student::class.java)) 或者  .asResponse<Student>()
     */
    constructor(type: Type) : super(type)

    @Throws(IOException::class)
    override fun onParse(response: okhttp3.Response): T {
        val type: Type = ParameterizedTypeImpl[ApiResponse::class.java, mType] //获取泛型类型
        val data: ApiResponse<T> = response.convert(type)
        val t = data.data //获取data字段
        val tData = t as? ApiPagerResponse<*>
        if (tData !=null) {
            //如果返回值值列表封装类，且是第一页并且空数据 那么给空异常 让界面显示空
            if (tData.isRefresh() && tData.isEmpty()) {
                throw ParseException(NetConstant.EMPTY_CODE, data.errorMsg, response)
            }
        }
        // errCode 不等于 SUCCESS_CODE，抛出异常
        if (data.errorCode != NetConstant.SUCCESS_CODE) {
            throw ParseException(data.errorCode.toString(), data.errorMsg, response)
        }
        return t
    }
}