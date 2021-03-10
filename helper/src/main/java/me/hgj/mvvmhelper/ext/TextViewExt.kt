package me.hgj.mvvmhelper.ext

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView


/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */

/**
 * 优化输入框 只抽取afterTextChanged方法
 */
fun EditText.afterTextChange(afterTextChanged: (String) -> Unit) {

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}

/**
 * 获取文本
 */
fun TextView.textString(): String {
    return text.toString()
}
/**
 * 获取去除空字符串的文本
 */
fun TextView.textStringTrim(): String {
    return textString().trim()
}
/**
 * 文本是否为空
 */
fun TextView.isEmpty(): Boolean {
    return textString().isEmpty()
}
/**
 * 去空字符串后文本是否为空
 */
fun TextView.isTrimEmpty(): Boolean {
    return this.textStringTrim().isEmpty()
}

fun EditText.showPwd(isChecked:Boolean){
    inputType = if (isChecked) {
        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    } else {
        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }
    setSelection(textString().length)
}