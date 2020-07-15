package com.example.apiservice.common

import com.example.apiservice.common.service.GenericInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

abstract class BaseServiceBuilder<T : Any?> protected constructor(interceptor: Interceptor? = GenericInterceptor()) {
    protected val mBuilder: Retrofit.Builder
    abstract fun build(): T

    init {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.interceptors().clear()
        okHttpClientBuilder.protocols(Arrays.asList(Protocol.HTTP_1_1))
        okHttpClientBuilder
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .retryOnConnectionFailure(false)
            .addInterceptor(interceptor)
        val client = okHttpClientBuilder.build()
        mBuilder = Retrofit.Builder()
            .baseUrl("")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
    }
}