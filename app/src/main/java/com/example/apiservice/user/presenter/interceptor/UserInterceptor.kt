package com.example.apiservice.user.presenter.interceptor

import com.example.apiservice.user.model.ModelData
import rx.Observable

interface UserInterceptor {
    fun getListUser(page: Int): Observable<ModelData>
}