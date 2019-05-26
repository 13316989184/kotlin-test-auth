package com.control.back.halo.authorization.shiro.realm

import java.util.Date
import java.util.HashSet

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authc.UnknownAccountException
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

import com.control.back.halo.basic.commons.Constants
import com.control.back.halo.basic.entity.User
import com.control.back.halo.manage.entity.Admin
import com.control.back.halo.manage.entity.Function
import com.control.back.halo.manage.entity.Role
import com.control.back.halo.manage.service.IAdminService

/**

 * @author zhaohuiliang
 */
@Component
class UserRealm : AuthorizingRealm() {

    @Autowired
    private val adminService: IAdminService? = null

    override fun doGetAuthorizationInfo(principals: PrincipalCollection): AuthorizationInfo {
        val username = principals.primaryPrincipal as String
        val authorizationInfo = SimpleAuthorizationInfo()
        val admin = adminService!!.findByUsername(username)
        val roles = admin.roles
        val shiroPermissions = HashSet<String>()
        for (role in roles!!) {
            val funcs = role.functions
            if (funcs != null) {
                for (function in funcs) {
                    shiroPermissions.add(function.shiroPermission!!)
                }
            }
        }

        authorizationInfo.stringPermissions = shiroPermissions
        return authorizationInfo
    }

    @Throws(AuthenticationException::class)
    override fun doGetAuthenticationInfo(token: AuthenticationToken): AuthenticationInfo {
        val username = token.principal as String
        val admin = adminService!!.findByUsername(username)
        val password = String(token.credentials as CharArray)

        if (admin == null) {
            throw UnknownAccountException()
        }
        val user = admin.user ?: throw UnknownAccountException("账号或密码不正确")
        // 账号不存在
        // 密码错误
        if (password != user.password) {
            throw IncorrectCredentialsException("账号或密码不正确")
        }
        // 账号锁定
        // if (admin.getLocked() == 0) { throw new
        // LockedAccountException("账号已被锁定,请联系管理员"); }

        val info = SimpleAuthenticationInfo(user, password, name)

        admin.loginTime = Date()
        adminService!!.update(admin)

        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val session = request.session
        session.setAttribute(Constants.USER_SESSION_ID, user)

        return info
    }

    public override fun clearCachedAuthorizationInfo(principals: PrincipalCollection?) {
        super.clearCachedAuthorizationInfo(principals)
    }

    public override fun clearCachedAuthenticationInfo(principals: PrincipalCollection) {
        super.clearCachedAuthenticationInfo(principals)
    }

    public override fun clearCache(principals: PrincipalCollection) {
        super.clearCache(principals)
    }

    fun clearAllCachedAuthorizationInfo() {
        authorizationCache.clear()
    }

    fun clearAllCachedAuthenticationInfo() {
        authenticationCache.clear()
    }

    fun clearAllCache() {
        clearAllCachedAuthenticationInfo()
        clearAllCachedAuthorizationInfo()
    }

}
