package com.nytimes.mostpopular.database

import android.annotation.SuppressLint
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        RoomArticleEntity::class
    ], version = 1, exportSchema = false
)
@SuppressLint("SyntheticAccessor")
abstract class RoomArticleDatabase : RoomDatabase() {

    /**
     * [articleDao]
     * @brief Abstract Function to return [RoomArticleDao] instance
     * @return: RoomArticleDao
     * @date: 03/27/2019
     * @author: atul.ps
     * */
    abstract fun articleDao(): RoomArticleDao
}