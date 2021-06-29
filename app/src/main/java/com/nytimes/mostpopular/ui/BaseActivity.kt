package com.nytimes.mostpopular.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
    }

    /**
     * [initDataBinding]
     * @brief Function to initialize common data binding layout and viewModel
     * @author atul.ps
     * @date 03/27/2019
     */
    abstract fun initDataBinding()
}
