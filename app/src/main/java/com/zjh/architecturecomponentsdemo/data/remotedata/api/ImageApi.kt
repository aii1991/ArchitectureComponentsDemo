package com.zjh.architecturecomponentsdemo.data.remotedata.api

import com.zjh.architecturecomponentsdemo.data.enitity.Image
import com.zjh.architecturecomponentsdemo.network.RspBean
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author zjh
 * 2017/8/21
 */
interface ImageApi{
    @GET("data/福利/{pageSize}/{currentPage}")
    fun getImages(@Path("pageSize") pageSize:Int,@Path("currentPage") currentPage:Int): Flowable<RspBean<List<Image>>>
}