package com.example.apiservice.user.model

import com.example.apiservice.common.entity.TypeData
import com.example.apiservice.common.entity.TypeDataView
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class Result(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("species")
    val species: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("origin")
    val origin: Origin,

    @SerializedName("location")
    val location: Location,

    @SerializedName("image")
    val image: String,

    @SerializedName("episode")
    val episode: List<String>,

    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val created: String


) : TypeDataView {
    override var getDataType: Int
        get() = TypeData.PERSON_RESULT.value
        set(value) {}
}