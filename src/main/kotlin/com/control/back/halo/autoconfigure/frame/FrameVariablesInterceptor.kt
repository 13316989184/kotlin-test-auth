package com.control.back.halo.autoconfigure.frame

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

@Component
@ConfigurationProperties(prefix = "halo.frame")
class FrameVariablesInterceptor : HandlerInterceptorAdapter() {

    var variables: Map<String, String>? = null

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, modelAndView: ModelAndView?) {
        if (modelAndView != null && variables != null && !variables!!.isEmpty()) {
            val keySet = variables!!.keys
            for (key in keySet) {
                modelAndView.addObject(key, variables!![key])
            }
        }
    }
}
