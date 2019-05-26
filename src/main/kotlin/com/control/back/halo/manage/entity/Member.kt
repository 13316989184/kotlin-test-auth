package com.control.back.halo.manage.entity

import java.util.Date

import javax.persistence.Entity
import javax.persistence.Table

import com.control.back.halo.basic.entity.BaseEntity

@Entity
@Table(name = "sys_member")
class Member : BaseEntity() {

    //名字
    var name: String? = null
    //性别
    var sex: Int? = null
    // 生日
    var birthday: Date? = null
    //头像地址
    var headUrl: String? = null

    companion object {

        /**

         */
        private val serialVersionUID = -3157026182471199653L
    }
}