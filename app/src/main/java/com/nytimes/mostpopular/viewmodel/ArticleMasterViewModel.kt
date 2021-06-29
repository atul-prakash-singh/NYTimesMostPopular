package com.nytimes.mostpopular.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nytimes.mostpopular.database.RoomArticleEntity
import com.nytimes.mostpopular.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class ArticleMasterViewModel @Inject constructor(private val repository: ArticleRepository) : BaseViewModel() {

    var liveData: LiveData<PagedList<RoomArticleEntity>>? = null
    val progressBarVisibility = ObservableBoolean(true)

    /**
     * [fetchMostPopularArticles]
     * @brief Function to provide LiveData for Most Popular Articles
     * @return LiveData<PagedList<RoomArticleEntity>>
     * @author atul.ps
     * @date 03/27/2019
     */
    suspend fun fetchMostPopularArticles(): LiveData<PagedList<RoomArticleEntity>> =
        LivePagedListBuilder(
            repository.getMostPopularArticles(),
            PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setEnablePlaceholders(true)
                .build()
        ).build()

    companion object {
        const val PAGE_SIZE = 20
    }
}