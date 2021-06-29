package com.nytimes.mostpopular.database

import androidx.room.Dao
import androidx.room.Query
import androidx.paging.DataSource

@Dao
abstract class RoomArticleDao : RoomBaseDao<RoomArticleEntity>() {

    @Query("SELECT * FROM article_table")
    abstract fun getArticles(): DataSource.Factory<Int, RoomArticleEntity>

    @Query("SELECT COUNT(id) from article_table")
    abstract fun getArticleCount() : Int
}
