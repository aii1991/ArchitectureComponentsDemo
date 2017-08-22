package com.zjh.architecturecomponentsdemo.ui

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.TypedValue
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zjh.architecturecomponentsdemo.R
import com.zjh.architecturecomponentsdemo.data.bean.ImageBean
import com.zjh.architecturecomponentsdemo.data.remotedata.req.PagingParam
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LifecycleActivity(), BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private lateinit var mImageViewModel:ImageViewModel
    private lateinit var mAdapter: BaseQuickAdapter<ImageBean,BaseViewHolder>
    private val mParam: PagingParam = PagingParam.createParam()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        loadData()
    }

    fun initView(){
        mRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        mAdapter = object: BaseQuickAdapter<ImageBean,BaseViewHolder>(R.layout.main_item){
            override fun convert(helper: BaseViewHolder?, item: ImageBean?) {
                Glide.with(this@MainActivity)
                        .load(item?.url)
                        .into(helper?.getView<ImageView>(R.id.iv))
            }
        }
        mAdapter.setOnLoadMoreListener(this,mRecyclerView)
        mRecyclerView.adapter = mAdapter

        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(resources.getColor(android.R.color.white))
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24f, resources.displayMetrics).toInt())
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light)
        mSwipeRefreshLayout.setOnRefreshListener(this)
        mSwipeRefreshLayout.isRefreshing = true

        mImageViewModel = ViewModelProviders.of(this).get(ImageViewModel::class.java)
        mImageViewModel.mImageBeanListLiveData.value = mAdapter.data
        mImageViewModel.mImageBeanListLiveData.observe(this, Observer { //监听livedata
            if (mSwipeRefreshLayout.isRefreshing){
                mSwipeRefreshLayout.isRefreshing = false
            }

            if (it != null){
                mAdapter.addData(it as List<ImageBean>)
                if (it.size < mParam.pageSize){
                    mAdapter.loadMoreEnd()
                }
            }else{
                mAdapter.loadMoreFail()
            }
        })
    }

    private fun loadData() {
        mImageViewModel.loadData(mParam,Consumer {
            Toast.makeText(this,"加载数据失败",Toast.LENGTH_SHORT).show()
        })
    }

    override fun onLoadMoreRequested() {
        mParam.currentPage ++
        mImageViewModel.loadData(mParam, Consumer {
            mParam.currentPage --
            mAdapter.loadMoreFail()
        },Action {
            mAdapter.loadMoreComplete()
        })
    }

    override fun onRefresh() {
        mAdapter.data.clear()
        mParam.currentPage = 1
        mImageViewModel.loadData(mParam, Consumer {
            Toast.makeText(this,"刷新数据失败",Toast.LENGTH_SHORT).show()
        })
    }
}
