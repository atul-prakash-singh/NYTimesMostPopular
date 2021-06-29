package com.nytimes.mostpopular.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.mostpopular.R
import com.nytimes.mostpopular.database.RoomArticleEntity
import com.nytimes.mostpopular.ui.adapter.viewholder.ArticleMasterViewHolder
import com.nytimes.mostpopular.ui.listener.IArticleClickListener
import com.nytimes.mostpopular.viewmodel.ArticleMasterItemViewModel

class ArticleMasterAdapter(
    private val inflater: LayoutInflater,
    private val clickListener: IArticleClickListener
) : PagedListAdapter<RoomArticleEntity, RecyclerView.ViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ArticleMasterViewHolder(
            inflater.inflate(R.layout.item_article, parent, false),
            ArticleMasterItemViewModel(),
            clickListener)

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val record = getItem(position)
        if (record != null)
            (holder as ArticleMasterViewHolder).setItem(record)
    }

    companion object {

        private val diffCallBack =
            object : DiffUtil.ItemCallback<RoomArticleEntity>() {
                override fun areItemsTheSame(
                    oldItem: RoomArticleEntity,
                    newItem: RoomArticleEntity
                ) = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: RoomArticleEntity,
                    newItem: RoomArticleEntity
                ) = oldItem == newItem
            }
    }
}
