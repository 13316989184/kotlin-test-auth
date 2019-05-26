package com.control.back.halo.manage.dao

import org.springframework.stereotype.Repository

import com.control.back.halo.basic.dao.IBaseDao
import com.control.back.halo.manage.entity.Role

@Repository
interface IRoleDao : IBaseDao<Role, Long>
