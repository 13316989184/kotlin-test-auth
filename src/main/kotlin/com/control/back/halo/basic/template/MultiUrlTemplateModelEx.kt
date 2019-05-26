package com.control.back.halo.basic.template

import org.apache.commons.lang3.StringUtils

import com.control.back.halo.basic.commons.Constants

import freemarker.template.TemplateMethodModelEx
import freemarker.template.TemplateModelException

class MultiUrlTemplateModelEx : TemplateMethodModelEx {

    /** 原始域名：含新域名编号所在位置的占位符  */
    /**

     * 功能描述: <br></br>
     * 〈功能详细描述〉

     * @return String 返回值
     */
    /**

     * 功能描述: <br></br>
     * 〈功能详细描述〉

     * @param host 参考说明
     */
    var host: String? = null

    var isNowRefush = false

    /** 后缀 /min.js /js  */
    var suffix: String? = null

    @Throws(TemplateModelException::class)
    override fun exec(arguments: List<*>?): Any {
        if (null == arguments || arguments.size == 0) {
            return ""
        }
        // 获取资源文件名
        var url = if (arguments[0] == null) "" else arguments[0].toString()

        if (StringUtils.isBlank(url)) {
            return ""
        }

        if (!host!!.endsWith("/")) {
            host = host!! + "/"
        }

        if (url.startsWith("/")) {
            url = url.substring(1)
        }

        if (!suffix!!.startsWith(".")) {
            suffix = "." + suffix!!
        }

        val result = StringBuffer()
        result.append(host).append(url).append(suffix)

        if (isNowRefush) {
            result.append("?t=").append(Constants.RESET_TIME)
        }

        return result.toString()
    }

}
