package com.zjh.architecturecomponentsdemo.network


import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.zjh.architecturecomponentsdemo.BaseConfig
import com.zjh.architecturecomponentsdemo.MyApplication
import com.zjh.architecturecomponentsdemo.network.interceptor.RspCheckInterceptor
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URI
import java.util.concurrent.TimeUnit

/**
 * @author zjh
 * *  2016/3/1
 */
class RetrofitManager private constructor() {
    private var mRetrofit: Retrofit? = null
    //    private CookieJarImpl mCookie;
    internal lateinit var mCookieJar: ClearableCookieJar
    internal lateinit var mPersistentCookieJar: SharedPrefsCookiePersistor

    init {
        initRetrofit()
    }

    private object HandlerRetrofitManager {
        val mRetrofitManager = RetrofitManager()
    }

    /**
     * 初始化retorfit配置
     */
    private fun initRetrofit() {
        val LoginInterceptor = HttpLoggingInterceptor()
        //        mCookie = new CookieJarImpl(new MemoryCookieStore());
        mPersistentCookieJar = SharedPrefsCookiePersistor(MyApplication.instance!!)
        mCookieJar = PersistentCookieJar(SetCookieCache(), mPersistentCookieJar)
        LoginInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()

        builder.addInterceptor(RspCheckInterceptor())
        if (BaseConfig.DEBUG) {
            builder.addInterceptor(LoginInterceptor)
        }

//        builder.addInterceptor(ReqAddTokenInterceptor())

        builder.connectTimeout(BaseConfig.CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        builder.readTimeout(BaseConfig.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        builder.writeTimeout(BaseConfig.WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(true)
        builder.cookieJar(mCookieJar) //cookie配置
        builder.hostnameVerifier { hostname, session -> true }

//        val sslParams = HttpsUtils.getSslSocketFactory(null, null, null)//SSL配置
//        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
        val client = builder.build()
        mRetrofit = Retrofit.Builder()
                .baseUrl(BaseConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    fun reLoad() {
        initRetrofit()
    }

    fun <T> createReq(reqServer: Class<T>): T {
        return mRetrofit!!.create(reqServer)
    }

    fun removeCookie() {
        mCookieJar.clear()
    }

    /**
     * 持久化cookie
     */
    fun persistentCookie() {
        mPersistentCookieJar.saveAll(mCookieJar.loadForRequest(HttpUrl.get(URI.create(BaseConfig.BASE_URL))))
    }

    companion object {
        val instance: RetrofitManager
            get() = HandlerRetrofitManager.mRetrofitManager
    }

}
