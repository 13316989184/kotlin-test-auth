package com.control.back.halo.test

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

import javax.xml.parsers.ParserConfigurationException
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

import org.dom4j.Document
import org.hibernate.internal.util.xml.Origin
import org.hibernate.internal.util.xml.XmlDocument
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

/**

 * @author hongliang.dinghl SAX文档解析
 */
class LoadXml : XmlDocument {

    fun createXml(fileName: String) {
        println("<<$fileName>>")
    }

    fun parserXml(fileName: String) {
        val saxfac = SAXParserFactory.newInstance()
        try {
            val saxparser = saxfac.newSAXParser()
            val `is` = FileInputStream(fileName)
            saxparser.parse(`is`, MySAXHandler())
        } catch (e: ParserConfigurationException) {
            e.printStackTrace()
        } catch (e: SAXException) {
            e.printStackTrace()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun getDocumentTree(): Document? {
        // TODO Auto-generated method stub
        return null
    }

    override fun getOrigin(): Origin? {
        // TODO Auto-generated method stub
        return null
    }

    companion object {
        /**

         */
        private val serialVersionUID = 481447048653646949L

        @JvmStatic fun main(args: Array<String>) {
            val xml = LoadXml()
            xml.parserXml("E:\\halo_source\\halo.back.control\\master\\src\\test\\resources\\aa.xml")
        }
    }
}

internal class MySAXHandler : DefaultHandler() {
    var hasAttribute = false
    var attributes: Attributes? = null

    @Throws(SAXException::class)
    override fun startDocument() {
        println("文档开始打印了")
    }

    @Throws(SAXException::class)
    override fun endDocument() {
        println("文档打印结束了")
    }

    @Throws(SAXException::class)
    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        if (attributes!!.length > 0) {
            this.attributes = attributes
            this.hasAttribute = true
        }
    }

    @Throws(SAXException::class)
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        if (hasAttribute && attributes != null) {
            for (i in 0..attributes!!.length - 1) {
                if (attributes!!.getValue(0) != null && attributes!!.getValue(0).trim { it <= ' ' } != "") {
                    println(attributes!!.getQName(0) + attributes!!.getValue(0))
                }
            }
        }
    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray?, start: Int, length: Int) {
        if (String(ch!!, start, length).trim { it <= ' ' }.length > 0) {
            println(String(ch, start, length))
        }
    }
}
