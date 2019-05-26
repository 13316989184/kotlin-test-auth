package com.control.back.halo.manage.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

import com.control.back.halo.basic.controller.BaseController
import com.control.back.halo.manage.entity.Admin
import com.control.back.halo.manage.service.IAdminService
import com.control.back.halo.manage.service.IRoleService

@Controller
@RequestMapping("/admin")
class AdminController : BaseController() {

    @Autowired
    private val adminService: IAdminService? = null

    @Autowired
    private val roleService: IRoleService? = null

    @RequestMapping("", "index")
    fun index(model: Model): String {
        model.addAttribute("admins", adminService!!.findAll())
        return "admin/index"
    }

    @RequestMapping("/remove")
    fun remove(id: Long): String {
        adminService!!.delete(id)
        return "redirect:index.html"
    }

    @RequestMapping("/oauth")
    fun oauth(id: Long, model: Model): String {
        model.addAttribute("admin", adminService!!.find(id))
        model.addAttribute("roles", roleService!!.findAll())
        return "authorization/userIndex"
    }

    @RequestMapping("/oauthsubmit")
    fun oauthsubmit(id: Long, roleIds: Array<Long>): String {
        adminService!!.saveAdminRoles(id, roleIds)
        return "redirect:index.html"
    }

    @RequestMapping("/saveOrUpdate")
    fun saveOrUpdate(admin: Admin): String {
        adminService!!.saveOrUpdate(admin)
        return "redirect:index.html"
    }
}
