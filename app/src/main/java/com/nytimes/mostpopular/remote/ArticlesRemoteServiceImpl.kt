package com.nytimes.mostpopular.remote

import android.content.Context
import com.nytimes.mostpopular.di.RetrofitClientInstance
import com.nytimes.mostpopular.model.Results
import retrofit2.Call
import javax.inject.Inject

class ArticleRemoteServiceImpl @Inject constructor(
    private val context: Context
) : ArticleRemoteService {

    override fun getMostPopularArticles(limit: Int, apiKey: String): Call<Results> =
        RetrofitClientInstance.getRetrofitInstance(context)
            .create(ArticleRemoteService::class.java)
            .getMostPopularArticles()
}
