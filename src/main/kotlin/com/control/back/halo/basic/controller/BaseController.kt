package com.control.back.halo.basic.controller

import java.util.Date

import org.springframework.beans.propertyeditors.StringTrimmerEditor
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder

import com.control.back.halo.basic.beans.DateEditor

/**
 * Created by zhaohl on 2015/5/8.
 */
open class BaseController {

    @InitBinder
    protected fun initBinder(webDataBinder: WebDataBinder) {
        webDataBinder.registerCustomEditor(String::class.java, StringTrimmerEditor(true))
        webDataBinder.registerCustomEditor(Date::class.java, DateEditor(true))
    }

    companion object {

        protected val ERROR_PAGE = "/admin/common/error"
    }
}
