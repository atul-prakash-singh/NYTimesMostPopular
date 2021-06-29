package com.nytimes.mostpopular

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nytimes.mostpopular.di.RetrofitClientInstance
import com.nytimes.mostpopular.model.Results
import com.nytimes.mostpopular.remote.ArticleRemoteService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val call = RetrofitClientInstance.getRetrofitInstance(context = applicationContext)
            .create(ArticleRemoteService::class.java)
            .getMostPopularArticles()

        call.enqueue(object : Callback<Results> {
            override fun onResponse(
                call: Call<Results>,
                response: Response<Results>
            ) {
                Timber.d("onResponse ${response.body()}")
            }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                Timber.d("onFailure  ${t.localizedMessage}")
            }
        })
    }
}