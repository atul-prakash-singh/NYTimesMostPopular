package com.nytimes.mostpopular.model

import com.google.gson.annotations.SerializedName
import com.nytimes.mostpopular.model.Image

data class Media(

    @SerializedName("copyright")
    val copyRight:  String,

    @SerializedName(value = "media-metadata")
    val metaData: List<Image>
)
