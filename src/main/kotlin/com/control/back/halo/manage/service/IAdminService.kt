package com.control.back.halo.manage.service

import com.control.back.halo.basic.service.IBaseService
import com.control.back.halo.manage.entity.Admin

interface IAdminService : IBaseService<Admin, Long> {

    fun findByUsername(username: String): Admin

    fun saveOrUpdate(admin: Admin)

    fun saveAdminRoles(id: Long, roles: Array<Long>): Boolean?

}
