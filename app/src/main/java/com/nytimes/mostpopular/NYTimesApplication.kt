package com.nytimes.mostpopular

import android.app.Application
import com.nytimes.mostpopular.remote.ArticleRemoteService
import com.nytimes.mostpopular.repository.ArticleRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class NYTimesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        plantTimber()

        fetchAndSaveMostPopularArticles()
    }

    private fun plantTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    @Inject lateinit var repository: ArticleRepository
    @Inject lateinit var remoteService: ArticleRemoteService

    private fun fetchAndSaveMostPopularArticles() {
        CoroutineScope(Dispatchers.Main).launch {
            repository.fetchAndSaveMostPopularArticles(remoteService)
        }
    }
}