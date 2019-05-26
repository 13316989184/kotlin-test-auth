package com.control.back.halo.basic.service.impl

import java.io.Serializable
import java.util.Arrays

import javax.transaction.Transactional

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification

import com.control.back.halo.basic.dao.IBaseDao
import com.control.back.halo.basic.entity.BaseEntity
import com.control.back.halo.basic.service.IBaseService

@Transactional
abstract class BaseServiceImpl<T : BaseEntity, ID : Serializable> : IBaseService<T, ID> {

    abstract val baseDao: IBaseDao<T, ID>

    override fun find(id: ID): T {
        return baseDao.findOne(id)
    }

    override fun findAll(): List<T> {
        return baseDao.findAll()
    }

    override fun findList(ids: Array<ID>): List<T> {
        val idList = Arrays.asList(*ids)
        return baseDao.findAll(idList)
    }

    override fun findList(spec: Specification<T>, sort: Sort): List<T> {
        return baseDao.findAll(spec, sort)
    }

    override fun findAll(pageable: Pageable): Page<T> {
        return baseDao.findAll(pageable)
    }

    override fun count(): Long {
        return baseDao.count()
    }

    override fun count(spec: Specification<T>): Long {
        return baseDao.count(spec)
    }

    override fun exists(id: ID): Boolean {
        return baseDao.exists(id)
    }

    override fun save(entity: T) {
        baseDao.save(entity)
    }

    fun save(entitys: Iterable<T>) {
        baseDao.save(entitys)
    }

    override fun update(entity: T): T {
        return baseDao.saveAndFlush(entity)
    }

    override fun delete(id: ID) {
        baseDao.delete(id)
    }

    override fun deleteByIds(vararg ids: ID) {
        if (ids != null) {
            for (i in ids.indices) {
                val id = ids[i]
                this.delete(id)
            }
        }
    }

    override fun delete(entitys: Array<T>) {
        val tList = Arrays.asList(*entitys)
        baseDao.delete(tList)
    }

    override fun delete(entitys: Iterable<T>) {
        baseDao.delete(entitys)
    }

    override fun delete(entity: T) {
        baseDao.delete(entity)
    }

    override fun findList(ids: Iterable<ID>): List<T> {
        return baseDao.findAll(ids)
    }

    override fun findAll(spec: Specification<T>, pageable: Pageable): Page<T> {
        return baseDao.findAll(spec, pageable)
    }

}
