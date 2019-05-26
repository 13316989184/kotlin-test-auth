package com.control.back.halo.autoconfigure.druid

import java.sql.SQLException

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import com.alibaba.druid.pool.DruidDataSource
import com.alibaba.druid.support.http.StatViewServlet
import com.alibaba.druid.support.http.WebStatFilter

/**
 * Created by halo on 16-1-29. 1.使用阿里的druid作为datasource
 */
@Configuration
class DataSourceConfiguration {

    @Bean
    fun druidDataSource(@Value("\${spring.datasource.driver-class-name}") driver: String,
                        @Value("\${spring.datasource.url}") url: String,
                        @Value("\${spring.datasource.username}") username: String,
                        @Value("\${spring.datasource.password}") password: String): DataSource {

        val druidDataSource = DruidDataSource()
        druidDataSource.driverClassName = driver
        druidDataSource.url = url
        druidDataSource.username = username
        druidDataSource.password = password

        try {
            druidDataSource.setFilters("stat, wall")
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return druidDataSource
    }

    @Bean
    fun druidServlet(): ServletRegistrationBean {
        val reg = ServletRegistrationBean()
        reg.setServlet(StatViewServlet())
        reg.addUrlMappings("/druid/*")
        reg.addInitParameter("loginUsername", "admin")
        reg.addInitParameter("loginPassword", "admin")
        reg.addInitParameter("resetEnable", "false")
        return reg
    }

    @Bean
    fun filterRegistrationBean(): FilterRegistrationBean {
        val filterRegistrationBean = FilterRegistrationBean()
        filterRegistrationBean.filter = WebStatFilter()
        filterRegistrationBean.addUrlPatterns("/*")
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
        return filterRegistrationBean
    }
}
