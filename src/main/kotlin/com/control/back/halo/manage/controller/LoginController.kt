package com.control.back.halo.manage.controller

import com.control.back.halo.manage.service.impl.RoleServiceImpl
import org.apache.log4j.Logger
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.LockedAccountException
import org.apache.shiro.authc.UnknownAccountException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.crypto.hash.Sha256Hash
import org.apache.shiro.subject.Subject
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@RequestMapping("/login")
@Controller
class LoginController {

    val logger: Logger = Logger.getLogger(LoginController::class.java);

    @RequestMapping("", "index")
    fun loginView(): String {
        return "/authorization/login"
    }

    @RequestMapping(value = "/submit", method = arrayOf(RequestMethod.POST))
    fun submit(username: String, password: String): String {
        try {
            val subject = SecurityUtils.getSubject()
            // sha256加密
            val passwordHash = Sha256Hash(password).toHex()
            val token = UsernamePasswordToken(username, passwordHash)
            subject.login(token)
        } catch (e: UnknownAccountException) {
            logger.error("UnknownAccountException", e);
            return "redirect:index.html"
        } catch (e: IncorrectCredentialsException) {
            logger.error("IncorrectCredentialsException", e);
            return "redirect:index.html"
        } catch (e: LockedAccountException) {
            logger.error("LockedAccountException", e);
            return "redirect:index.html"
        } catch (e: AuthenticationException) {
            logger.error("AuthenticationException", e);
            return "redirect:index.html"
        }

        return "redirect:/index.html"
    }
}
