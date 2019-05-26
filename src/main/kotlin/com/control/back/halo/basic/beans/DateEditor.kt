package com.control.back.halo.basic.beans

import java.beans.PropertyEditorSupport
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

import org.apache.commons.lang3.time.DateUtils

/**
 * Created by zhaohl on 2015/5/8.
 */
class DateEditor : PropertyEditorSupport {

    private var emptyAsNull: Boolean = false
    private var dateFormat = "yyyy-MM-dd HH:mm:ss"

    constructor(emptyAsNull: Boolean) {
        this.emptyAsNull = emptyAsNull
    }

    constructor(emptyAsNull: Boolean, dateFormat: String) {
        this.emptyAsNull = emptyAsNull
        this.dateFormat = dateFormat
    }

    override fun getAsText(): String {
        val date = value as Date
        return if (date != null) SimpleDateFormat(this.dateFormat).format(date) else ""
    }

    override fun setAsText(text: String?) {
        if (text == null) {
            value = null
        } else {
            val str = text.trim { it <= ' ' }
            if (this.emptyAsNull && "" == str)
                value = null
            else
                try {
                    value = DateUtils.parseDate(str, *DATE_PATTERNS)
                } catch (e: ParseException) {
                    value = null
                }

        }
    }

    companion object {
        val DATE_PATTERNS = arrayOf("yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss")
    }
}
