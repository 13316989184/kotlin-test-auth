package com.control.back.halo.manage.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

import com.control.back.halo.basic.entity.BaseEntity

@Entity
@Table(name = "sys_function")
class Function : BaseEntity() {

    // fields
    /**
     * Return the value associated with the column: NAME
     */
    /**
     * Set the value related to the column: NAME

     * @param name
     * *            the NAME value
     */
    @Column(length = 100)
    var name: String? = null
    /**
     * Return the value associated with the column: URL
     */
    /**
     * Set the value related to the column: URL

     * @param url
     * *            the URL value
     */
    @Column(length = 200)
    var url: String? = null
    @Column(length = 2, nullable = false)
    var type: Int? = null
    // 节点菜单维护
    /**
     * Return the value associated with the column: FUNCS
     */
    /**
     * Set the value related to the column: FUNCS

     * @param funcs
     * *            the FUNCS value
     */
    @Column(length = 200)
    var funcs: String? = null
    /**
     * Return the value associated with the column: DESCRIPTION
     */
    /**
     * Set the value related to the column: DESCRIPTION

     * @param description
     * *            the DESCRIPTION value
     */
    @Column(length = 250)
    var description: String? = null
    @Column(length = 10, nullable = false)
    var level: Int? = null
    @Column(name = "is_active", nullable = false)
    var active: Boolean? = null
    // 权限
    @Column(length = 200)
    var shiroPermission: String? = null

    // many to one
    /**
     * Return the value associated with the column: PARENT_ID
     */
    /**
     * Set the value related to the column: PARENT_ID

     * @param parent
     * *            the PARENT_ID value
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Function? = null

    /**
     * Return the value associated with the column: roles
     */
    /**
     * Set the value related to the column: roles

     * @param roles
     * *            the roles value
     */
    @ManyToMany(mappedBy = "functions")
    var roles: Set<Role>? = null
    /**
     * Return the value associated with the column: child
     */
    /**
     * Set the value related to the column: child

     * @param child
     * *            the child value
     */
    @OneToMany(mappedBy = "parent")
    var child: Set<Function>? = null

    val treeName: String?
        get() = name

    val treeParent: Function?
        get() = parent

    override fun hashCode(): Int {
        val prime = 31
        var result = super.hashCode()
        result = prime * result + if (id == null) 0 else id.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (!super.equals(obj)) return false
        if (javaClass != obj!!.javaClass) return false
        val other = obj as Function?
        if (id == null) {
            if (other!!.id != null) return false
        } else if (id != other!!.id) return false
        return true
    }

    override fun toString(): String {
        return super.toString()
    }

    companion object {
        private val serialVersionUID = 1L

        var FUNCTION_TYPE_CATEGORY: Int? = 1
        var FUNCTION_TYPE_MENU: Int? = 2
        var FUNCTION_TYPE_HREF: Int? = 3
        var FUNCTION_TYPE_BUTTON: Int? = 4

        /**
         * 功能集的分隔符
         */
        val FUNC_SPLIT = "@"
    }
}
