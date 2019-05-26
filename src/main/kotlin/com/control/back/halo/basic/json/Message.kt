package com.control.back.halo.basic.json

/**
 * Created by zhaohl on 2015/5/5.
 */
class Message {
    var type: Message.Type? = null
    var content: String? = null

    enum class Type {
        success, warn, error
    }

    constructor() {}

    constructor(type: Message.Type, content: String) {
        this.type = type
        this.content = content
    }

    constructor(type: Message.Type, content: String, vararg args: Any) {
        this.type = type
        this.content = content
    }

    companion object {

        fun success(content: String, vararg args: Any): Message {
            return Message(Message.Type.success, content, *args)
        }

        fun warn(content: String, vararg args: Any): Message {
            return Message(Message.Type.warn, content, *args)
        }

        fun error(content: String, vararg args: Any): Message {
            return Message(Message.Type.error, content, *args)
        }
    }
}
