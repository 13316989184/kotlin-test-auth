package com.control.back.halo.basic.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean

import com.control.back.halo.basic.entity.BaseEntity

import java.io.Serializable

@NoRepositoryBean
interface IBaseDao<T : BaseEntity, ID : Serializable> : JpaRepository<T, ID>, JpaSpecificationExecutor<T>
