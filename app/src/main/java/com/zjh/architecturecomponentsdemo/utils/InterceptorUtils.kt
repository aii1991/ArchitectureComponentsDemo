package com.zjh.architecturecomponentsdemo.utils


import java.io.IOException
import java.nio.charset.Charset

import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSource

/**
 * @author zjh
 * *  2016/9/2
 */
object InterceptorUtils {
    private val UTF8 = Charset.forName("UTF-8")

    fun getRspData(responseBody: ResponseBody): String? {
        var rspData: String? = null
        try {
            val contentLength = responseBody.contentLength()
            val source = responseBody.source()
            source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer()
            var charset = UTF8
            val contentType = responseBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(UTF8)
            }
            if (contentLength == 0L) {
                rspData = buffer.clone().readString(charset)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return rspData
    }

    @Throws(IOException::class)
    fun getReqData(requestBody: RequestBody): String {
        val buffer = Buffer()
        requestBody.writeTo(buffer)
        return buffer.readString(UTF8)
    }

    fun changeRspData(response: Response, rspData: String): Response {
        val contentType = response.body().contentType()
        val body = ResponseBody.create(contentType, rspData)
        return response.newBuilder().body(body).build()
    }
}
