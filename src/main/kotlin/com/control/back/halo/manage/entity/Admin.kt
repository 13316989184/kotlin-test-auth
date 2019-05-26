package com.control.back.halo.manage.entity

import java.util.HashSet

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToOne
import javax.persistence.Table

import com.control.back.halo.basic.entity.BaseEntity
import com.control.back.halo.basic.entity.User

@Entity
@Table(name = "sys_admin")
class Admin : BaseEntity() {
    // fields
    var loginTime: java.util.Date? = null
    var loginCount: Int? = null

    // many to one
    @OneToOne(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    var user: User? = null

    // many to one
    @OneToOne(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    var member: Member? = null

    // collections
    /**
     * Return the value associated with the column: roles
     */
    /**
     * Set the value related to the column: roles

     * @param roles
     * *            the roles value
     */
    @ManyToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.LAZY)
    @JoinTable(name = "sys_admin_role", joinColumns = arrayOf(JoinColumn(name = "admin_id")), inverseJoinColumns = arrayOf(JoinColumn(name = "role_id")))
    var roles: MutableSet<Role>? = null
        get() = field

    // 被锁定
    var locked: Int? = null

    /**
     * 获得用户(登录)名

     * @return
     */
    val userName: String?
        get() {
            if (user != null) {
                return user!!.username
            } else {
                return null
            }
        }

    /**
     * 获得登录名

     * @return
     */
    val loginName: String?
        get() {
            if (user != null) {
                return user!!.username
            } else {
                return null
            }
        }

    fun addToRole(role: Role) {
        if (roles == null) {
            val h = HashSet<Role>()
            h.add(role)
            roles = h
        } else {
            roles!!.add(role)
        }
    }

    override fun equals(obj: Any?): Boolean {
        if (null == obj) return false
        if (obj !is Admin)
            return false
        else {
            val admin = obj as Admin?
            if (null == this.id || null == admin!!.id)
                return false
            else
                return this.id == admin.id
        }
    }

    companion object {
        private val serialVersionUID = 1L
    }

}
