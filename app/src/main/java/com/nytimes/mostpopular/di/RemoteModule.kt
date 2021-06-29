package com.nytimes.mostpopular.di

import android.content.Context
import com.nytimes.mostpopular.remote.ArticleRemoteService
import com.nytimes.mostpopular.remote.ArticleRemoteServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun provideArticleRemoteService(
        @ApplicationContext context: Context
    ): ArticleRemoteService {
        return ArticleRemoteServiceImpl(context)
    }
}