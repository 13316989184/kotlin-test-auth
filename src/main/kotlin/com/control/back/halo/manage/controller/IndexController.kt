package com.control.back.halo.manage.controller

import org.apache.shiro.SecurityUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import com.control.back.halo.basic.controller.BaseController
import com.control.back.halo.basic.utils.UserUtils
import com.control.back.halo.manage.service.IFunctionService

@Controller
class IndexController : BaseController() {

    @Autowired
    private val functionService: IFunctionService? = null

    @RequestMapping("", "index")
    fun index(mv: Model): String {
        mv.addAttribute("functions", functionService!!.loadFunctionByAdminId(UserUtils.currentUser!!.getId()))
        return "index"
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = arrayOf(RequestMethod.GET))
    fun logout(): String {
        SecurityUtils.getSubject().logout()
        return "redirect:login.html"
    }

    /**
     * 注册

     * @return
     */
    @RequestMapping("/register")
    fun registerView(): String {
        return "/authorization/register"
    }

}
