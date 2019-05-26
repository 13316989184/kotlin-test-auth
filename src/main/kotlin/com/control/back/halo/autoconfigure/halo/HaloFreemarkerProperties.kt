package com.control.back.halo.autoconfigure.halo

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Created by zhaohuiliang on 2017/6/29.
 */
@ConfigurationProperties(prefix = "halo.freemarker")
class HaloFreemarkerProperties {

    var settings: Map<String, Map<String, String>>? = null

    var variables: Map<String, String>? = null

}