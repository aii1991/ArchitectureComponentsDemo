package com.zjh.architecturecomponentsdemo.network

/**
 * @author zjh
 * 2017/8/21
 */
data class RspBean<T>(var error:Boolean,var results:T)