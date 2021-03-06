package com.example.apiservice.common.service

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class GenericInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request?.newBuilder()
            ?.addHeader("Content-Type", "application/json")
            ?.addHeader("Accept", "application/json")
            ?.build()

        //Log.e("GenericInterceptor::: ", request.toString())
        return chain.proceed(request)

    }
}