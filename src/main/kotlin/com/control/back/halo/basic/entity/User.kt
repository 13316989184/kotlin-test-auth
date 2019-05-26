package com.control.back.halo.basic.entity

import java.util.Date

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "sys_user")
class User : BaseEntity() {

    @get:Column(nullable = false, updatable = false, unique = true)
    var username: String? = null
    @get:Column(nullable = false)
    var password: String? = null
    /**
     * 最后一次登录时间
     */
    var lastLoginTime: Date? = null
    var loginIp: String? = null

    override fun hashCode(): Int {
        val prime = 31
        var result = super.hashCode()
        result = prime * result + if (username == null) 0 else username!!.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj)
            return true
        if (!super.equals(obj))
            return false
        if (javaClass != obj!!.javaClass)
            return false
        val other = obj as User?
        if (username == null) {
            if (other!!.username != null)
                return false
        } else if (username != other!!.username)
            return false
        return true
    }

    override fun toString(): String {
        return "Admin{username='$username', password='$password', lastLoginTime:'$lastLoginTime', loginIp='$loginIp'}"
    }

    companion object {
        private val serialVersionUID = -7519486823153844426L
    }
}