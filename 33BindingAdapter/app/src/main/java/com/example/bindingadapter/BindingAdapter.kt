package com.example.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("showImage")
fun loadImage(view: ImageView, imageUrl: String) {
    if (imageUrl.isEmpty()) {
        return
    }
    Glide.with(view.context)
        .load(imageUrl)
        .into(view)
}