package com.example.apiservice.user.model


import android.os.Parcel
import android.os.Parcelable
import com.example.apiservice.common.entity.TypeData
import com.example.apiservice.common.entity.TypeDataView
import com.google.gson.annotations.SerializedName

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

    /*override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Result> = object : Parcelable.Creator<Result> {
            override fun newArray(size: Int): Array<Result?> = arrayOfNulls<Result>(size)
            override fun createFromParcel(source: Parcel): Result = Result(source)
        }
    }

    constructor(source: Parcel) : this(
        id = source.readInt(),
        name = source.readString(),
        status = source.readString(),
        species = source.readString(),
        type = source.readString(),
        gender = source.readString(),
        origin = source.

    )


    override fun describeContents(): kotlin.Int = 0*/

}



