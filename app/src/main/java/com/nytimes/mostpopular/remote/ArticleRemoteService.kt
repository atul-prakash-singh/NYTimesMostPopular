package com.nytimes.mostpopular.remote

import com.nytimes.mostpopular.model.Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleRemoteService {

    @GET("mostpopular/v2/viewed/{limit}.json")
    fun getMostPopularArticles(
        @Path("limit") limit: Int = DEFAULT_LIMIT,
        @Query("api-key") apiKey: String = API_KEY): Call<Results>

    companion object {
        private const val DEFAULT_LIMIT = 7
        private const val API_KEY = "VkSCQpf6gV1xApIqp8w2TsPNzrAA576t"
    }
}