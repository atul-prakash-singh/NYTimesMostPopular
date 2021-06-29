package com.nytimes.mostpopular.viewmodel

import androidx.databinding.ObservableField

class ArticleDetailViewModel : BaseViewModel() {

    val uri = ObservableField<String>("")
    val imageUrl = ObservableField<String>("")
    val detailAbstract = ObservableField<String>("")
    val title = ObservableField<String>("")
    val byLine = ObservableField<String>("")
    val publishedDate = ObservableField<String>("")
}