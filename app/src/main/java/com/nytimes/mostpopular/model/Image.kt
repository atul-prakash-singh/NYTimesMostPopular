package com.nytimes.mostpopular.model

import com.google.gson.annotations.SerializedName

data class Image(

    @SerializedName(value = "url")
    val url: String,

    @SerializedName(value = "format")
    val format: String
)
