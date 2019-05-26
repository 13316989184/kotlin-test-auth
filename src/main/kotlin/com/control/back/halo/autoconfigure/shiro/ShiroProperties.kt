package com.control.back.halo.autoconfigure.shiro

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Configuration properties for Shiro.

 * @author halo
 */
@ConfigurationProperties(prefix = "shiro")
class ShiroProperties {
    /**
     * Custom Realm
     */
    var realm: String? = null
    /**
     * URL of login
     */
    var loginUrl: String? = null
    /**
     * URL of success
     */
    var successUrl: String? = null
    /**
     * URL of unauthorized
     */
    var unauthorizedUrl: String? = null
    /**
     * filter chain
     */
    var filterChainDefinitions: Map<String, String>? = null

    /**

     */
    var ehacheConfig: String? = null


}
