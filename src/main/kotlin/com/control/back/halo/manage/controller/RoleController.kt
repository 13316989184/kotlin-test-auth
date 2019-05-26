package com.control.back.halo.manage.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import com.control.back.halo.basic.json.Result
import com.control.back.halo.manage.entity.Role
import com.control.back.halo.manage.service.IRoleService

@Controller
@RequestMapping("/role")
class RoleController {

    @Autowired
    private val roleService: IRoleService? = null

    @RequestMapping("", "index")
    fun index(model: Model): String {
        model.addAttribute("roles", roleService!!.findAll())
        return "role/index"
    }

    @RequestMapping("/remove")
    fun remove(id: Long): String {
        roleService!!.delete(id)
        return "redirect:index.html"
    }

    @RequestMapping("/oauth")
    fun oauth(id: Long, model: Model): String {
        model.addAttribute("role", roleService!!.find(id))
        return "authorization/roleIndex"
    }

    @RequestMapping("/loadFunctionTree")
    @ResponseBody
    fun function(id: Long, model: Model): Result {
        return Result(true, "success", roleService!!.loadTreeAndMarkRoleFunctions(id))
    }

    @RequestMapping("/oauthsubmit")
    @ResponseBody
    fun oauthsubmit(@RequestBody role: Role): Result {
        return Result(roleService!!.saveRoleFunctions(role.id, role.functions), "success", null)
    }

    @RequestMapping("/saveOrUpdate")
    fun saveOrUpdate(role: Role): String {
        roleService!!.saveOrUpdate(role)
        return "redirect:index.html"
    }
}
