package com.control.back.halo.test

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import com.control.back.halo.SampleSpringApplication
import com.control.back.halo.manage.dao.IAdminDao
import com.control.back.halo.manage.entity.Admin

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
class ApplicationTests {

    @Autowired
    private val adminDao: IAdminDao? = null

    @Test
    @Throws(Exception::class)
    fun testFind() {
        val admin = adminDao!!.findByUsername("admin")
        println("第一次查询：" + admin.id)

        val admin1 = adminDao!!.findByUsername("admin")
        println("第二次查询：" + admin1.id)
    }

}
