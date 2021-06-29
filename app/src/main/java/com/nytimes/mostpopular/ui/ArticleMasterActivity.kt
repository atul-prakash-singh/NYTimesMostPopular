package com.nytimes.mostpopular.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.mostpopular.R
import com.nytimes.mostpopular.database.RoomArticleEntity
import com.nytimes.mostpopular.databinding.ActivityArticleMasterBinding
import com.nytimes.mostpopular.ui.adapter.ArticleMasterAdapter
import com.nytimes.mostpopular.ui.listener.IArticleClickListener
import com.nytimes.mostpopular.utility.Constants
import com.nytimes.mostpopular.viewmodel.ArticleMasterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticleMasterActivity : BaseActivity(), IArticleClickListener {

    private lateinit var binding: ActivityArticleMasterBinding

    private val viewModel: ArticleMasterViewModel by viewModels()
    private lateinit var adapter: ArticleMasterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
        observeLiveData()
    }

    override fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this@ArticleMasterActivity, R.layout.activity_article_master)
        binding.viewModel = viewModel
    }

    /**
     * [initRecyclerView]
     * @brief Function to initialize and setup article recycler view adapter
     * @author atul.ps
     * @date 03/27/2019
     */
    private fun initRecyclerView() {
        adapter = ArticleMasterAdapter(layoutInflater, this@ArticleMasterActivity)

        val layoutManager = LinearLayoutManager(this@ArticleMasterActivity,
            RecyclerView.VERTICAL, false)
        layoutManager.isSmoothScrollbarEnabled = true
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.adapter = adapter
    }

    /**
     * [observeLiveData]
     * @brief Function to observe Most Popular Articles live data from Room Persistence Storage
     * @author atul.ps
     * @date 03/27/2019
     */
    private fun observeLiveData() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.liveData = viewModel.fetchMostPopularArticles()
            viewModel.liveData?.observe(this@ArticleMasterActivity,
                Observer { pagedList ->
                    adapter.submitList(pagedList)
                    viewModel.progressBarVisibility.set(false)
                })
        }
    }

    override fun onItemClick(item: RoomArticleEntity) {
        startActivity(Intent(this@ArticleMasterActivity, ArticleDetailActivity::class.java).apply {
            putExtra(Constants.BUNDLE_KEY_URL, item.imageLarge)
            putExtra(Constants.BUNDLE_KEY_TITLE, item.title)
            putExtra(Constants.BUNDLE_KEY_BY_LINE, item.byLine)
            putExtra(Constants.BUNDLE_KEY_ABSTRACT, item.detailAbstract)
            putExtra(Constants.BUNDLE_KEY_PUBLISHED_DATE, item.publishedDate)
        })
    }
}