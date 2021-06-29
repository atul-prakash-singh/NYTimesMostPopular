package com.nytimes.mostpopular.di

import android.content.Context
import androidx.room.Room
import com.nytimes.mostpopular.database.RoomArticleDao
import com.nytimes.mostpopular.database.RoomArticleDatabase
import com.nytimes.mostpopular.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideRoomArticleDatabase(@ApplicationContext appContext: Context): RoomArticleDatabase {
        return Room.databaseBuilder(
            appContext,
            RoomArticleDatabase::class.java,
            "articles-db"
        ).build()
    }

    @Provides
    fun provideRoomArticleDao(appDatabase: RoomArticleDatabase): RoomArticleDao {
        return appDatabase.articleDao()
    }

    @Provides
    fun provideArticleRepository(articleDao: RoomArticleDao): ArticleRepository {
        return ArticleRepository(articleDao)
    }
}