package com.control.back.halo.manage.entity

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

import com.control.back.halo.basic.entity.BaseEntity

@Entity
@Table(name = "sys_role")
class Role : BaseEntity() {

    // fields
    /**
     * Return the value associated with the column: NAME
     */
    /**
     * Set the value related to the column: NAME

     * @param name
     * *            the NAME value
     */
    var name: String? = null
    /**
     * Return the value associated with the column: DESCRIPTION
     */
    /**
     * Set the value related to the column: DESCRIPTION

     * @param description
     * *            the DESCRIPTION value
     */
    var description: String? = null

    // collections
    /**
     * Return the value associated with the column: admins
     */
    /**
     * Set the value related to the column: admins

     * @param admins
     * *            the admins value
     */
    @ManyToMany(mappedBy = "roles")
    var admins: Set<Admin>? = null

    /**
     * Return the value associated with the column: functions
     */
    /**
     * Set the value related to the column: functions

     * @param functions
     * *            the functions value
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_function", joinColumns = arrayOf(JoinColumn(name = "role_id")), inverseJoinColumns = arrayOf(JoinColumn(name = "function_id")))
    var functions: Set<Function>? = null

    companion object {

        /**

         */
        private val serialVersionUID = 820694530776191567L
    }
}
