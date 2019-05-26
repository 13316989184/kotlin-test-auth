package com.control.back.halo.basic.json

class Result {

    var success: Boolean? = null
    var error: String? = null
    var message: String? = null

    var item: Any? = null

    constructor() {}

    constructor(success: Boolean?, message: String, item: Any?) {
        this.success = success
        this.message = message
        this.item = item
    }

    companion object {

        fun error(message: String): Result {
            val r = Result(false, message, null)
            r.error = message
            return r
        }

        fun success(message: String): Result {
            val r = Result(true, message, null)
            return r
        }
    }
}
