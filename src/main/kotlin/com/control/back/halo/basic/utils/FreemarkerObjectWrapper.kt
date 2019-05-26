package com.control.back.halo.basic.utils

import freemarker.template.*
import org.joda.time.base.AbstractInstant

class FreemarkerObjectWrapper : DefaultObjectWrapper(Configuration.VERSION_2_3_26) {

    @Throws(TemplateModelException::class)
    override fun wrap(obj: Any?): TemplateModel {
        if (obj is AbstractInstant) return SimpleDate(obj.toDate(), TemplateDateModel.DATETIME)

        return super.wrap(obj)
    }
}
