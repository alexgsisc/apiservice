package com.example.apiservice.user.presenter.interceptor

import android.content.Context
import com.example.apiservice.user.api.repository.UserRepositoryImp
import com.example.apiservice.user.model.ModelData
import rx.Observable


class UserInterceptorImp(val context: Context) : UserInterceptor {

    private val userRepository = UserRepositoryImp()

    override fun getListUser(page: Int): Observable<ModelData> {
        return userRepository.getListUser(page)
    }
}