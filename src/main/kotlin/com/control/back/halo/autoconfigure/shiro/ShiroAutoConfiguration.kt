package com.control.back.halo.autoconfigure.shiro

import org.apache.shiro.cache.CacheManager
import org.apache.shiro.cache.MemoryConstrainedCacheManager
import org.apache.shiro.mgt.DefaultSecurityManager
import org.apache.shiro.realm.Realm
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.beans.BeanInstantiationException
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Import

@Configuration
@EnableConfigurationProperties(ShiroProperties::class)
@Import(ShiroManager::class)
class ShiroAutoConfiguration {
    @Autowired
    private val properties: ShiroProperties? = null

    @Bean(name = arrayOf("realm"))
    @DependsOn("lifecycleBeanPostProcessor")
    @ConditionalOnMissingBean
    fun realm(): Any? {
        val relmClass = properties!!.realm
        try {
            return BeanUtils.instantiate(Class.forName(relmClass))
        } catch (e: BeanInstantiationException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * 用户授权信息Cache
     */
    @Bean(name = arrayOf("shiroCacheManager"))
    @ConditionalOnMissingBean
    fun cacheManager(): CacheManager {
        return MemoryConstrainedCacheManager()
    }

    @Bean(name = arrayOf("securityManager"))
    @ConditionalOnMissingBean
    fun securityManager(): DefaultSecurityManager {
        val sm = DefaultWebSecurityManager()
        sm.cacheManager = cacheManager()
        return sm
    }

    @Bean(name = arrayOf("shiroFilter"))
    @DependsOn("securityManager")
    @ConditionalOnMissingBean
    fun getShiroFilterFactoryBean(securityManager: DefaultSecurityManager, realm: Realm): ShiroFilterFactoryBean {
        securityManager.setRealm(realm)

        val shiroFilter = ShiroFilterFactoryBean()
        shiroFilter.securityManager = securityManager
        shiroFilter.loginUrl = properties!!.loginUrl
        shiroFilter.successUrl = properties!!.successUrl
        shiroFilter.unauthorizedUrl = properties!!.unauthorizedUrl
        shiroFilter.filterChainDefinitionMap = properties!!.filterChainDefinitions
        return shiroFilter
    }
}
