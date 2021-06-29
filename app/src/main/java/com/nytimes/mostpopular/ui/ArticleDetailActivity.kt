package com.nytimes.mostpopular.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nytimes.mostpopular.R
import com.nytimes.mostpopular.databinding.ActivityArticleDetailBinding
import com.nytimes.mostpopular.utility.Constants
import com.nytimes.mostpopular.viewmodel.ArticleDetailViewModel

class ArticleDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityArticleDetailBinding

    private lateinit var viewModel: ArticleDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.apply {
            imageUrl.set(intent.extras?.getString(Constants.BUNDLE_KEY_URL) ?: "")
            title.set(intent.extras?.getString(Constants.BUNDLE_KEY_TITLE) ?: "")
            byLine.set(intent.extras?.getString(Constants.BUNDLE_KEY_BY_LINE) ?: "")
            detailAbstract.set(intent.extras?.getString(Constants.BUNDLE_KEY_ABSTRACT) ?: "")
            publishedDate.set(intent.extras?.getString(Constants.BUNDLE_KEY_PUBLISHED_DATE) ?: "")
        }
    }

    override fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_detail)

        viewModel = ArticleDetailViewModel()
        binding.viewModel = viewModel
    }
}