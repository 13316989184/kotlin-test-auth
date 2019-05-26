package com.control.back.halo.basic.entity.vo

import java.io.Serializable

/**
 * 树型节点<br></br>

 * @author halo
 * *
 * @see
 * @since V1.0
 */
/**

 */
class TreeNode : Serializable {

    /**
     * 节点ID
     */
    /**
     * @return the id
     */
    /**
     * @param id
     * *            the id to set
     */
    var id: Long? = null

    /**
     * 节点名称
     */
    /**
     * @return the name
     */
    /**
     * @param name
     * *            the name to set
     */
    var name: String? = null

    /**
     * 父节点ID
     */
    /**
     * @return the pid
     */
    /**
     * @param pid
     * *            the pid to set
     */
    var pid: Long? = null

    /**
     * 组织类型
     */
    /**
     * @return the type
     */
    /**
     * @param type
     * *            the type to set
     */
    var type: Int = 0

    /**
     * @return the status
     */
    /**
     * @param status
     * *            the status to set
     */
    var status = 1

    /**
     * @return the iconSkin
     */
    /**
     * @param iconSkin
     * *            the iconSkin to set
     */
    var iconSkin = ""

    var isParent = true

    var isChecked = false

    var isOpen = false

    companion object {

        /**
         */
        private const val serialVersionUID = -693675736472494761L
    }

}
