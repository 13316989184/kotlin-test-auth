package com.control.back.halo.basic.log4j

import org.apache.log4j.Level
import org.apache.log4j.LogManager

class Logger protected constructor(log4jLogger: org.apache.log4j.Logger) {

    private var log4jLogger: org.apache.log4j.Logger? = null

    init {
        this.log4jLogger = log4jLogger
    }

    /**

     * 功能描述: <br></br>

     * <pre>
     * 例子：输出你的名字是halo
     * `
     * Logger.info("你的名字是%s","halo");
    ` *

    </pre> *

     * @param message 内容格式
     * *
     * @param args 参数
     * *
     * @see String.format
     * @since 2016-06-03
     */
    fun info(message: String, vararg args: Any) {
        log(Level.INFO, message, *args)
    }

    /**

     * @param message
     * *
     * @param args
     * *
     * @see .info
     */
    fun debug(message: String, vararg args: Any) {
        log(Level.DEBUG, message, *args)
    }

    /**

     * @param message
     * *
     * @param args
     * *
     * @see .info
     */
    fun warn(message: String, vararg args: Any) {
        log(Level.WARN, message, *args)
    }

    /**

     * @param message
     * *
     * @param args
     * *
     * @see .info
     */
    fun error(message: String, vararg args: Any) {
        log(Level.ERROR, message, *args)
    }

    /**

     * @param message
     * *
     * @param e
     * *
     * @param args
     * *
     * @see .info
     */
    fun info(message: String, e: Throwable, vararg args: Any) {
        log(Level.INFO, message, e, *args)
    }

    /**

     * @param message
     * *
     * @param e
     * *
     * @param args
     * *
     * @see .info
     */
    fun debug(message: String, e: Throwable, vararg args: Any) {
        log(Level.DEBUG, message, e, *args)
    }

    /**

     * @param message
     * *
     * @param e
     * *
     * @param args
     * *
     * @see .info
     */
    fun warn(message: String, e: Throwable, vararg args: Any) {
        log(Level.WARN, message, e, *args)
    }

    /**

     * @param message
     * *
     * @param e
     * *
     * @param args
     * *
     * @see .info
     */
    fun error(message: String, e: Throwable, vararg args: Any) {
        log(Level.ERROR, message, e, *args)
    }

    /**

     * @param message
     * *
     * @param e
     * *
     * @param args
     * *
     * @see .info
     */
    fun log(level: Level, message: String, vararg args: Any) {
        var message = message
        if (args.size > 0) {
            message = String.format(message, *args)
        }
        log4jLogger!!.log(level, message)
    }

    /**

     * @param message
     * *
     * @param e
     * *
     * @param args
     * *
     * @see .info
     */
    fun log(level: Level, message: String, e: Throwable, vararg args: Any) {
        var message = message
        if (args.size > 0) {
            message = String.format(message, *args)
        }
        log4jLogger!!.log(level, message, e)
    }

    companion object {

        /**

         * @param message
         * *
         * @param e
         * *
         * @param args
         * *
         * @see .info
         */
        fun getLogger(clazz: Class<*>): Logger {
            return Logger(LogManager.getLogger(clazz.name))
        }
    }
}