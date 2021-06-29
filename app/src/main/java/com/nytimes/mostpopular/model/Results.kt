package com.nytimes.mostpopular.model

import com.google.gson.annotations.SerializedName
import com.nytimes.mostpopular.model.Article

data class Results(

    @SerializedName("results")
    val articles: List<Article>
)
