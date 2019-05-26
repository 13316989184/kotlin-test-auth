package com.control.back.halo.basic.utils

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

import com.control.back.halo.basic.commons.Constants
import com.control.back.halo.basic.entity.User

object UserUtils {

    val currentUserName: String?
        get() = currentUser!!.username

    val currentUser: User?
        get() {
            val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
            val session = request.session
            var user = session.getAttribute(Constants.USER_SESSION_ID);
            if (user != null) {
                return user as User?
            }
            return null
        }

}
