package com.control.back.halo.basic.template

import com.control.back.halo.autoconfigure.halo.HaloFreemarkerProperties
import freemarker.core.Environment
import freemarker.template.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by zhaohuiliang on 2017/6/29.
 */
@Component
class FhaloTypeDirective : TemplateDirectiveModel {

    @Autowired
    val properties: HaloFreemarkerProperties? = null;

    override fun execute(env: Environment?, params: MutableMap<Any?, Any?>?, loopVars: Array<out TemplateModel>?, body: TemplateDirectiveBody?) {
        val out = env!!.getOut()
        if(params!!.get("value") == null){
            return
        }

        if(params.get("setType") == null){
            return
        }
        val valueModel = params!!.get("value") as SimpleNumber
        val setTypeModel = params.get("setType") as SimpleScalar
        if (properties != null) {
            val setTypes = properties!!.settings!!.get(setTypeModel.asString) as Map<String, String>
            val text = setTypes.get(valueModel.toString());
            out.write(text)
        }
    }
}