package com.example.apiservice.user.api.repository

import com.example.apiservice.user.model.ModelData
import rx.Observable

interface UserRepository {
    fun getListUser(page: Int): Observable<ModelData>
}