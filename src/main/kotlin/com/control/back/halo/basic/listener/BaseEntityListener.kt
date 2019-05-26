package com.control.back.halo.basic.listener

import javax.persistence.PrePersist
import javax.persistence.PreUpdate

import org.joda.time.DateTime
import org.springframework.stereotype.Component

import com.control.back.halo.basic.entity.BaseEntity
import com.control.back.halo.basic.utils.UserUtils

@Component("baseEntityListener")
class BaseEntityListener {

    /**
     * Before add entity, init createDate and updateDate

     * @param baseEntity
     */
    @PrePersist
    fun initEntity(baseEntity: BaseEntity) {
        baseEntity.createdDate = DateTime.now()
        baseEntity.lastModifiedDate = DateTime.now()
        if (UserUtils.currentUser != null) {
            baseEntity.createdBy = UserUtils.currentUser
            baseEntity.lastModifiedBy = UserUtils.currentUser
        }
    }

    /**
     * Before update entity ,set updateDate
     */
    @PreUpdate
    fun updateEntity(baseEntity: BaseEntity) {
        baseEntity.lastModifiedDate = DateTime.now()
        if (UserUtils.currentUser != null) {
            baseEntity.lastModifiedBy = UserUtils.currentUser
        }
    }
}
