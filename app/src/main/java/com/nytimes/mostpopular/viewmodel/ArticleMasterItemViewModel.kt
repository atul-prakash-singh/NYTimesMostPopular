package com.nytimes.mostpopular.viewmodel

import androidx.databinding.ObservableField
import com.nytimes.mostpopular.utility.EventIdentifier

class ArticleMasterItemViewModel : BaseViewModel() {

    val uri = ObservableField<String>("")
    val title = ObservableField<String>("")
    val byLine = ObservableField<String>("")
    val publishedDate = ObservableField<String>("")

    fun onItemClick() = triggerEvent(EventIdentifier.ARTICLE_LIST_ITEM_CLICKED)
}
