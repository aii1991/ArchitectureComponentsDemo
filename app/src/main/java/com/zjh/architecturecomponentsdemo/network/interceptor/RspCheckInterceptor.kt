package com.zjh.architecturecomponentsdemo.network.interceptor


import com.zjh.architecturecomponentsdemo.utils.InterceptorUtils
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

/**
 * @author zjh
 * *  2016/8/31
 */
class RspCheckInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        try {
            val rspBody = response.body()
            val jsonObject = JSONObject(InterceptorUtils.getRspData(rspBody))
            val status = jsonObject.getInt("status")
            if (status < 200 || status >= 300) {
                throw IOException(jsonObject.getString("msg"))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            throw IOException("parase data error")
        } catch (e: Exception) {
            if (e is IOException) {
                throw e
            }
        }

        return response
    }
}
