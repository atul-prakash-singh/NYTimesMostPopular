package com.nytimes.mostpopular.utility

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["uri", "placeholder"], requireAll = false)
fun loadImage(view: ImageView, imageUri: String, placeholder: Drawable) {
    Glide.with(view.context)
        .load(imageUri)
        .placeholder(placeholder)
        .into(view)
}

@BindingAdapter(value = ["imageUri", "error"], requireAll = false)
fun loadCircularImage(view: ImageView, imageUri: String, error: Drawable) {
    Glide.with(view.context)
        .load(imageUri)
        .placeholder(error)
        .apply(RequestOptions.circleCropTransform())
        .into(view)
}