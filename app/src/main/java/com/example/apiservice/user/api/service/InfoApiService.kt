package com.example.apiservice.user.api.service

import com.example.apiservice.common.service.BaseServiceBuilders
import com.example.apiservice.user.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable


interface InfoApiService {

    //Lista de usurio
    @GET("character")
    fun getPerson(@Query("page") page: Int): Observable<ModelData>

    //Peticion por usuario
    @GET("character/{idUser}")
    fun getPersoId(@Path("idUser") idUser: Int): Observable<Result>

    class Builder() : BaseServiceBuilders<InfoApiService>() {
        override fun build(): InfoApiService {
            return retrofitBuild.build().create(InfoApiService::class.java)
        }
    }
}