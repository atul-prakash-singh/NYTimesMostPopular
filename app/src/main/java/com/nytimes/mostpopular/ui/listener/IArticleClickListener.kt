package com.nytimes.mostpopular.ui.listener

import com.nytimes.mostpopular.database.RoomArticleEntity

interface IArticleClickListener {

    fun onItemClick(item: RoomArticleEntity)
}
