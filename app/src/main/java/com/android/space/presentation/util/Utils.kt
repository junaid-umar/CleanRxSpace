package com.android.space.presentation.util

import android.widget.ImageView
import com.android.space.R
import com.bumptech.glide.Glide

object Utils {
    fun loadImageWithGlide(iv: ImageView, url: String) {
        iv.apply {

            Glide.with(context)
                .load(url)
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(this)
        }
    }

}