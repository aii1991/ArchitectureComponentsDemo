package com.zjh.architecturecomponentsdemo.data.remotedata.api

import com.zjh.architecturecomponentsdemo.data.bean.ImageBean
import com.zjh.architecturecomponentsdemo.data.remotedata.ReqUrl
import com.zjh.architecturecomponentsdemo.network.RspBean
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author zjh
 * 2017/8/21
 */
interface ImageApi{
    @GET(ReqUrl.IMAGE_REQ_URL)
    fun getImages(@Path("pageSize") pageSize:Int,@Path("currentPage") currentPage:Int): Flowable<RspBean<List<ImageBean>>>
}