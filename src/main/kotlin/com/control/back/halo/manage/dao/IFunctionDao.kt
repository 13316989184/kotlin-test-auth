package com.control.back.halo.manage.dao

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import com.control.back.halo.basic.dao.IBaseDao
import com.control.back.halo.manage.entity.Function

@Repository
interface IFunctionDao : IBaseDao<Function, Long> {

    @Query(nativeQuery = true, value = "select level from sys_function where type = ?1 order by level desc limit 1")
    fun maxFunctionLevel(type: Int?): Int?

    @Query(nativeQuery = true, value = "select level from sys_function t where t.parent_id = ?1 order by level desc limit 1")
    fun maxFunctionLevel(parentId: Long?): Int?

    @Query(nativeQuery = true, value = "SELECT sf.* FROM sys_admin_role sar, sys_role sr, sys_role_function srf, sys_function sf WHERE sar.role_id = sr.id AND sr.id = srf.role_id AND srf.function_id = sf.id AND sar.admin_id = :adminId  order by type asc")
    fun loadFunctionByAdminId(@Param("adminId") adminId: Long?): Set<Function>
}
