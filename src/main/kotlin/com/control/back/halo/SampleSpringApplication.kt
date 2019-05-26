package com.control.back.halo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.support.config.FastJsonConfig
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter

@SpringBootApplication
@EnableCaching
class SampleSpringApplication : WebMvcConfigurerAdapter() {

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>?) {
        super.configureMessageConverters(converters)
        val fastConverter = FastJsonHttpMessageConverter()
        val fastJsonConfig = FastJsonConfig()
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat)
        fastConverter.fastJsonConfig = fastJsonConfig
        converters!!.add(fastConverter)
    }

    @Bean
    fun registFilter(): FilterRegistrationBean {
        val registration = FilterRegistrationBean()
        registration.filter = OpenEntityManagerInViewFilter()
        registration.addUrlPatterns("/*")
        registration.order = 1
        return registration
    }

    companion object {

        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(SampleSpringApplication::class.java, *args)
        }
    }
}
