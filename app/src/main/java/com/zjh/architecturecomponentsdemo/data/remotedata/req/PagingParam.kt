package com.zjh.architecturecomponentsdemo.data.remotedata.req

/**
 * @author zjh
 * 2017/8/21
 */
data class PagingParam(var currentPage:Int = 1,var pageSize:Int = 15){
   companion object {
        fun createParam(): PagingParam{
            return PagingParam()
        }
   }
}