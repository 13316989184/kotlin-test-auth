package com.control.back.halo.manage.dao

import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

import com.control.back.halo.basic.dao.IBaseDao
import com.control.back.halo.manage.entity.Admin

@Repository
interface IAdminDao : IBaseDao<Admin, Long> {

    @Query("from Admin a where a.user.username = ?1")
    fun findByUsername(username: String): Admin

}
