package com.example.apiservice.user.api.repository

import com.example.apiservice.user.api.service.InfoApiService
import com.example.apiservice.user.model.ModelData
import rx.Observable

class UserRepositoryImp : UserRepository {
    var userApi: InfoApiService = InfoApiService.Builder().build()

    override fun getListUser(page: Int): Observable<ModelData> {
        return userApi.getPerson(page)
    }
}