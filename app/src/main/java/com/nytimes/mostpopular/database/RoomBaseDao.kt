package com.nytimes.mostpopular.database

import androidx.room.*

@Dao
abstract class RoomBaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEntity(item: T): Long

    @Update
    abstract fun updateEntity(item: T): Int

    @Delete
    abstract fun deleteEntity(item: T)
}
