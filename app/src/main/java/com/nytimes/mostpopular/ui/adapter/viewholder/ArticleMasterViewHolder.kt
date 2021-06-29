package com.nytimes.mostpopular.ui.adapter.viewholder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.mostpopular.database.RoomArticleEntity
import com.nytimes.mostpopular.databinding.ItemArticleBinding
import com.nytimes.mostpopular.ui.listener.IArticleClickListener
import com.nytimes.mostpopular.utility.EventIdentifier
import com.nytimes.mostpopular.viewmodel.ArticleMasterItemViewModel

class ArticleMasterViewHolder(
    view: View,
    private val viewModel: ArticleMasterItemViewModel,
    private val listener: IArticleClickListener
) : RecyclerView.ViewHolder(view) {

    private val binding: ItemArticleBinding? = DataBindingUtil.bind(view)
    private lateinit var item: RoomArticleEntity

    init {
        binding?.viewModel = viewModel
        setUpViewModelEventListener()
    }

    fun setItem(item: RoomArticleEntity) {

        this.item = item

        viewModel.apply {
            uri.set(item.imageThumbnail)
            title.set(item.title)
            byLine.set(item.byLine)
            publishedDate.set(item.publishedDate)
        }
    }

    private fun setUpViewModelEventListener() {
        viewModel.onEventReceived += { et ->
            if (et.type == EventIdentifier.ARTICLE_LIST_ITEM_CLICKED)
                listener.onItemClick(item)
        }
    }
}
