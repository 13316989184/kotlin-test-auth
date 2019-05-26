package com.control.back.halo.manage.service

import com.control.back.halo.basic.entity.vo.TreeNode
import com.control.back.halo.basic.service.IBaseService
import com.control.back.halo.manage.entity.Function
import com.control.back.halo.manage.entity.Role

interface IRoleService : IBaseService<Role, Long> {

    fun saveOrUpdate(role: Role)

    /**
     * 加载tree并且标记角色所属菜单

     * @param roleId
     * *
     * @return
     */
    fun loadTreeAndMarkRoleFunctions(roleId: Long): List<TreeNode>

    fun saveRoleFunctions(roleId: Long, functions: Set<Function>?): Boolean?
}
