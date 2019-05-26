package com.control.back.halo.basic.entity

import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

import org.hibernate.validator.constraints.Length
import org.springframework.data.jpa.domain.AbstractAuditable

import com.control.back.halo.basic.listener.BaseEntityListener

@MappedSuperclass
@EntityListeners(BaseEntityListener::class)
abstract class BaseEntity : AbstractAuditable<User, Long>() {
    @get:Length(min = 1, max = 1)
    var delFlag = DEL_FLAG_NORMAL // 删除标记（0：正常；1：删除；2：审核）


    public override fun setId(id: Long?) {
        super.setId(id)
    }

    companion object {

        private val serialVersionUID = -67188388306700736L

        val FIELD_DEL_FLAG = "delFlag"
        val DEL_FLAG_NORMAL = "0"
        val DEL_FLAG_DELETE = "1"
        val DEL_FLAG_AUDIT = "2"
    }

}