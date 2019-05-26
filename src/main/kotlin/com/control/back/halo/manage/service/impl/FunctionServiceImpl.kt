package com.control.back.halo.manage.service.impl


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.control.back.halo.basic.dao.IBaseDao
import com.control.back.halo.basic.entity.vo.TreeNode
import com.control.back.halo.basic.service.impl.BaseServiceImpl
import com.control.back.halo.manage.dao.IFunctionDao
import com.control.back.halo.manage.entity.Function
import com.control.back.halo.manage.service.IFunctionService

@Service
class FunctionServiceImpl : BaseServiceImpl<Function, Long>(), IFunctionService {

    @Autowired
    private val functionDao: IFunctionDao? = null

    override val baseDao: IBaseDao<Function, Long>
        get() = this.functionDao!!

    override fun maxFunctionLevel(type: Int?, parentId: Long?): Int? {
        if (parentId == null || parentId <= 0) {
            return functionDao!!.maxFunctionLevel(type)
        } else {
            return functionDao!!.maxFunctionLevel(parentId)
        }
    }

    override fun save(entity: Function) {
        var entity = entity
        if (entity.id == null) {
            var level: Int? = 0
            if (entity.parent != null && entity.parent!!.id != null) {
                level = functionDao!!.maxFunctionLevel(entity.parent!!.id)
                entity.parent = find(entity.parent!!.id)
            } else {
                level = functionDao!!.maxFunctionLevel(entity.type)
                entity.parent = null
            }
            level = (if (level == null) 0 else level) + 1
            entity.level = level
            entity.active = true
        } else {
            val entityFunction = find(entity.id)
            entityFunction.name = entity.name
            entityFunction.url = entity.url
            entityFunction.shiroPermission = entity.shiroPermission
            entity = entityFunction
        }
        super.save(entity)
    }

    override fun loadTree(): List<TreeNode> {
        val fArray = findAll()
        val tnArray = ArrayList<TreeNode>(fArray.size)
        for (f in fArray) {
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
            tnArray.add(node)
        }
        return tnArray
    }

    override fun loadFunctionByAdminId(adminId: Long?): Map<String, Set<Function>> {
        val funs = functionDao!!.loadFunctionByAdminId(adminId)
//        var mapfuns = HashMap<String, Set<Function>>()
        val mapfuns = hashMapOf<String, Set<Function>>()
        for (function in funs) {
            var id = "0"
            if (function.parent != null) {
                id = function.parent!!.id.toString()
            }

            if (mapfuns[id] != null) {
                var funset: HashSet<Function> = mapfuns[id] as HashSet<Function>
                funset.add(function)
            } else {
                //val tops = HashSet<Function>()
                val tops: Set<Function> = hashSetOf(function)
                mapfuns.put(id, tops)
            }
        }

        return mapfuns
    }

}
