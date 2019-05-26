package com.control.back.halo.manage.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import com.control.back.halo.basic.controller.BaseController
import com.control.back.halo.basic.entity.vo.TreeNode
import com.control.back.halo.basic.json.Result
import com.control.back.halo.manage.entity.Function
import com.control.back.halo.manage.service.IFunctionService

@Controller
@RequestMapping("/function")
class FunctionController : BaseController() {

    @Autowired
    private val functionService: IFunctionService? = null

    @RequestMapping("", "index")
    fun index(model: Model): String {
        model.addAttribute("functions", functionService!!.findAll())
        return "function/index"
    }

    @RequestMapping("/loadTreeNode")
    @ResponseBody
    fun loadTreeNode(): Result {
        val trArray = functionService!!.loadTree()
        return Result(true, "查询成功", trArray)
    }

    @RequestMapping("/add")
    fun addFunction(function: Function, model: Model): String {
        functionService!!.save(function)
        return "redirect:index.html"
    }

    @RequestMapping("/remove")
    fun remove(id: Long, model: Model): String {
        functionService!!.delete(id)
        return "redirect:index.html"
    }

    @RequestMapping("/findAll")
    @ResponseBody
    fun findUserFunctions(): Result {
        return Result(true, "查询成功", functionService!!.findAll())
    }
}
