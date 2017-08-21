package com.zjh.architecturecomponentsdemo


/**
 * @author zjh
 * *  2017/3/1
 * * 基础配置
 */
object BaseConfig {
    val DEBUG = true
    val PAGE_SIZE = 15 //分页大小

    //------网络层配置--------
    var BASE_URL = "http://gank.io/api/"
    //retrofit
    val CONNECT_TIMEOUT = 30 //网络连接超时时间(以秒为单位)
    val READ_TIMEOUT = 60 //网络读取超时时间(以秒为单位)
    val WRITE_TIMEOUT = 60 //网络写超时时间(以秒为单位)

}
