package com.control.back.halo.autoconfigure.shiro

import org.apache.shiro.mgt.DefaultSecurityManager
import org.apache.shiro.spring.LifecycleBeanPostProcessor
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.DependsOn

/**
 * Shiro Config Manager.

 * @author halo
 */
class ShiroManager {

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     */
    @Bean(name = arrayOf("lifecycleBeanPostProcessor"))
    @ConditionalOnMissingBean
    fun lifecycleBeanPostProcessor(): LifecycleBeanPostProcessor {
        return LifecycleBeanPostProcessor()
    }

    @Bean(name = arrayOf("defaultAdvisorAutoProxyCreator"))
    @ConditionalOnMissingBean
    @DependsOn("lifecycleBeanPostProcessor")
    fun defaultAdvisorAutoProxyCreator(): DefaultAdvisorAutoProxyCreator {
        val defaultAdvisorAutoProxyCreator = DefaultAdvisorAutoProxyCreator()
        defaultAdvisorAutoProxyCreator.isProxyTargetClass = true
        return defaultAdvisorAutoProxyCreator

    }

    @Bean
    @ConditionalOnMissingBean
    fun getAuthorizationAttributeSourceAdvisor(securityManager: DefaultSecurityManager): AuthorizationAttributeSourceAdvisor {
        val aasa = AuthorizationAttributeSourceAdvisor()
        aasa.securityManager = securityManager
        return AuthorizationAttributeSourceAdvisor()
    }
}
