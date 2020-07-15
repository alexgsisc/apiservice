package com.example.apiservice.common.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseServiceBuilders<T : Any> {
    var retrofitBuild: Retrofit.Builder
    lateinit var interceptors: Interceptor

    protected constructor(interceptor: Interceptor? = GenericInterceptor()) {
        this.interceptors = interceptor!!
    }

    init {
        //https://rickandmortyapi.com/api/character/?page=1
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        //okHttpClientBuilder.interceptors().clear()
        //okHttpClientBuilder.protocols(listOf(Protocol.HTTP_1_1))
        okHttpClientBuilder
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .retryOnConnectionFailure(false)
            //.addInterceptor(interceptors)
        val client = okHttpClientBuilder.build()
        retrofitBuild = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
    }


    abstract fun build(): T

}
