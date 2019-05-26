package com.control.back.halo.autoconfigure.halo

import com.control.back.halo.basic.template.FhaloTypeDirective
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer
import java.util.*
import kotlin.collections.HashMap


/**
 * Created by zhaohuiliang on 2017/6/29.
 */
@Configuration
@EnableConfigurationProperties(HaloFreemarkerProperties::class,FreeMarkerProperties::class)
class FreemarkerConfig {

    @Autowired
    protected var haloTypeDirective: FhaloTypeDirective? = null

    @Autowired
    protected var freemarkerProperties: HaloFreemarkerProperties? = null

    @Autowired
    protected var properties: FreeMarkerProperties? = null

    protected fun applyProperties(factory: FreeMarkerConfigurationFactory) {
        factory.setTemplateLoaderPaths(*this.properties!!.templateLoaderPath)
        factory.setPreferFileSystemAccess(this.properties!!.isPreferFileSystemAccess)
        factory.setDefaultEncoding(this.properties!!.charsetName)
        val settings = Properties()
        settings.putAll(this.properties!!.settings)
        factory.setFreemarkerSettings(settings)
        var fvaiables = HashMap<String, Any?>(freemarkerProperties!!.variables);
        fvaiables.put("haloType", haloTypeDirective)
        factory.setFreemarkerVariables(fvaiables);
    }

    @Bean
    fun freeMarkerConfigurer(): FreeMarkerConfigurer {
        val configurer = FreeMarkerConfigurer()
        applyProperties(configurer)
        return configurer
    }
}