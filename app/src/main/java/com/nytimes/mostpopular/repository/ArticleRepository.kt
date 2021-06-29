package com.nytimes.mostpopular.repository

import androidx.paging.DataSource
import com.nytimes.mostpopular.database.RoomArticleDao
import com.nytimes.mostpopular.database.RoomArticleEntity
import com.nytimes.mostpopular.model.Article
import com.nytimes.mostpopular.model.Results
import com.nytimes.mostpopular.remote.ArticleRemoteService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class ArticleRepository @Inject constructor(private val articleDao: RoomArticleDao) {

    suspend fun getMostPopularArticles(): DataSource.Factory<Int, RoomArticleEntity> =
        withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
            articleDao.getArticles()
        }

    private suspend fun insert(item: RoomArticleEntity) =
        withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
            articleDao.insertEntity(item)
        }

    private suspend fun isDatabaseEmpty() =
        withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
            articleDao.getArticleCount() <= 0
        }

    suspend fun fetchAndSaveMostPopularArticles(service: ArticleRemoteService) =
        withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {

            if (isDatabaseEmpty()) {
                service.getMostPopularArticles().enqueue(object : Callback<Results> {
                    override fun onResponse(call: Call<Results>, response: Response<Results>) {
                        Timber.d("onResponse() ${response.body()?.articles?.size}")
                        CoroutineScope(Dispatchers.Main).launch {
                            insertArticlesToDatabase(response.body()?.articles ?: emptyList())
                        }
                    }

                    override fun onFailure(call: Call<Results>, t: Throwable) {
                        Timber.e("onFailure() ${t.localizedMessage}")
                    }
                })
            }
        }

    suspend fun insertArticlesToDatabase(articles: List<Article>) {
        for (article in articles) {
            insert(
                RoomArticleEntity(
                    id = article.id,
                    url = article.url,
                    title = article.title,
                    byLine = article.byLine,
                    detailAbstract = article.detailAbstract,
                    publishedDate = article.publishedDate,
                    imageThumbnail = if (article.media.isNotEmpty())
                                        article.media[0].metaData[0].url
                                    else
                                        "",
                    imageLarge = if (article.media.isNotEmpty())
                                    article.media[0].metaData[2].url
                                else
                                    ""
                )
            )
        }
    }
}
