package com.control.back.halo.manage.service.impl

import java.util.HashSet

import org.apache.commons.lang3.StringUtils
import org.apache.shiro.crypto.hash.Sha256Hash
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import com.control.back.halo.basic.dao.IBaseDao
import com.control.back.halo.basic.entity.User
import com.control.back.halo.basic.log4j.Logger
import com.control.back.halo.basic.service.impl.BaseServiceImpl
import com.control.back.halo.manage.dao.IAdminDao
import com.control.back.halo.manage.entity.Admin
import com.control.back.halo.manage.entity.Member
import com.control.back.halo.manage.entity.Role
import com.control.back.halo.manage.service.IAdminService
import com.control.back.halo.manage.service.IRoleService

@Service
class AdminServiceImpl : BaseServiceImpl<Admin, Long>(), IAdminService {

    @Autowired
    private val adminDao: IAdminDao? = null

    @Autowired
    private val roleService: IRoleService? = null

    @Value("\${halo.freemarker.variables.default-role}")
    private val defaultUserRoleId: Long? = null

    override val baseDao: IBaseDao<Admin, Long>
        get() = this.adminDao!!

    override fun findByUsername(username: String): Admin {
        return adminDao!!.findByUsername(username)
    }

    override fun saveOrUpdate(admin: Admin) {
        if (admin.id != null) {
            val adminOne = find(admin.id)
            val user = adminOne.user
            if (admin.user != null) {
                user!!.username = admin.user!!.username
                if (StringUtils.isNoneBlank(admin.user!!.password)) {
                    val password = Sha256Hash(admin.user!!.password).toHex()
                    user.password = password
                }
            }
            val member = adminOne.member
            if (member == null) {
                adminOne.member = admin.member
            } else {
                if (admin.member != null) {
                    member.birthday = admin.member!!.birthday
                    member.sex = admin.member!!.sex
                    member.name = admin.member!!.name
                }
            }
            update(adminOne)
        } else {
            if (admin.user != null) {
                if (StringUtils.isNoneBlank(admin.user!!.password)) {
                    val password = Sha256Hash(admin.user!!.password).toHex()
                    admin.user!!.password = password
                }
            }
            val role = roleService!!.find(defaultUserRoleId!!)
            admin.addToRole(role)
            save(admin)
        }
    }

    override fun saveAdminRoles(id: Long, roles: Array<Long>): Boolean? {
        try {
            val admin = find(id)
            val rs = HashSet<Role>(roles.size)
            for (roleId in roles) {
                val role = roleService!!.find(roleId)
                rs.add(role)
            }
            admin.roles = rs
            super.save(admin)
        } catch (e: Exception) {
            logger.error("Exception params:[id:%s,roles:%s]", e, id!!.toString(), roles)
            return false
        }

        return true
    }

    companion object {

        private val logger = Logger.getLogger(AdminServiceImpl::class.java)
    }
}
