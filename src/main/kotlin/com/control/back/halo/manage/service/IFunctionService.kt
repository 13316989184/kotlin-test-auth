package com.control.back.halo.manage.service

import com.control.back.halo.basic.entity.vo.TreeNode
import com.control.back.halo.basic.service.IBaseService
import com.control.back.halo.manage.entity.Function

interface IFunctionService : IBaseService<Function, Long> {

    fun maxFunctionLevel(type: Int?, parentId: Long?): Int?

    fun loadTree(): List<TreeNode>

    fun loadFunctionByAdminId(adminId: Long?): Map<String, Set<Function>>
}
