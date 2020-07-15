package com.example.apiservice.user.model

import com.google.gson.annotations.SerializedName

data class ModelData (
    @SerializedName("info")
    var info: Info? = null,

    @SerializedName("results")
    val results: List<Result>
)