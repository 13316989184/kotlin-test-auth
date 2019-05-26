package com.control.back.halo.basic.commons

import java.text.SimpleDateFormat
import java.util.Date

/**
 * 常量类

 * @author
 */
object Constants {

    /**
     * 时间戳
     */
    val RESET_TIME = SimpleDateFormat("yyyyMMddhh").format(Date())

    /**
     * session key
     */
    val USER_SESSION_ID = "user_session_id"

}
