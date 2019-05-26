package com.control.back.halo.manage.service.impl

import java.util.ArrayList
import java.util.HashSet

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.alibaba.fastjson.JSON
import com.control.back.halo.basic.dao.IBaseDao
import com.control.back.halo.basic.entity.vo.TreeNode
import com.control.back.halo.basic.log4j.Logger
import com.control.back.halo.basic.service.impl.BaseServiceImpl
import com.control.back.halo.manage.dao.IRoleDao
import com.control.back.halo.manage.entity.Function
import com.control.back.halo.manage.entity.Role
import com.control.back.halo.manage.service.IFunctionService
import com.control.back.halo.manage.service.IRoleService

@Service
class RoleServiceImpl : BaseServiceImpl<Role, Long>(), IRoleService {

    @Autowired
    private val roleDao: IRoleDao? = null

    @Autowired
    private val functionService: IFunctionService? = null

    val logger = Logger.getLogger(RoleServiceImpl::class.java)

    override val baseDao: IBaseDao<Role, Long>
        get() = this.roleDao!!

    override fun saveOrUpdate(role: Role) {
        if (role.id != null) {
            val roleOne = find(role.id)
            roleOne.name = role.name
            update(roleOne)
        } else {
            save(role)
        }
    }

    override fun loadTreeAndMarkRoleFunctions(roleId: Long): List<TreeNode> {
        val role = find(roleId)
        val roleFunctionSet = role.functions

        val functionArray = functionService!!.findAll()
        val tnArray = ArrayList<TreeNode>(functionArray.size)
        for (f in functionArray) {
            val node = TreeNode()
            node.id = f.id
            if (f.parent == null) {
                node.pid = -1L
            } else {
                node.pid = f.parent!!.id
            }
            node.name = f.name
            if (f.child != null) {
                node.isParent = true
            }

            if (roleFunctionSet != null) {
                if (roleFunctionSet.contains(f)) {
                    node.isChecked = true
                }
            }
            tnArray.add(node)
        }

        return tnArray
    }

    override fun saveRoleFunctions(roleId: Long, functions: Set<Function>?): Boolean? {
        try {
            val role = find(roleId)
            if (functions != null) {
                val newFunctions = HashSet<Function>(functions.size)
                for (function in functions) {
                    newFunctions.add(functionService!!.find(function.id))
                }
                role.functions = newFunctions
            }
            super.save(role)
        } catch (e: Exception) {
            logger.error("Exception params:[roleId:%s,nodes:%s]", e, roleId, JSON.toJSONString(functions))
            return false
        }

        return true
    }
}
