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
    var interceptors: Interceptor

    protected constructor(interceptor: Interceptor? = GenericInterceptor()) {
        this.interceptors = interceptor!!
    }

    init {
        //https://rickandmortyapi.com/api/character/?page=1
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .connectTimeout(3, TimeUnit.MINUTES)
            .retryOnConnectionFailure(false)
        val client = okHttpClientBuilder.build()
        retrofitBuild = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
    }


    abstract fun build(): T

}
