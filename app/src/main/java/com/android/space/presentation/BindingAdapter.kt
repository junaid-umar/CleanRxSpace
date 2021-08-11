package com.android.space.presentation

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.space.domain.model.Blog
import com.android.space.domain.model.Resource
import com.android.space.domain.model.getData
import com.android.space.presentation.blog.list.BlogAdapter
import com.android.space.presentation.util.Utils
import com.google.android.material.snackbar.Snackbar

@BindingAdapter("bind:showLoadingOn")
fun showLoadingOn(view: View, item: Resource<Any>?) {
    view.visibility = if (item is Resource.Loading) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("bind:showErrorOn")
fun showErrorOn(view: View, item: Resource<Any>?) {
    if (item is Resource.Error) {
        Snackbar.make(
            view,
            item.message,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}


@BindingAdapter(value = ["bind:imageUrl", "placeholder"], requireAll = false)
fun showImage(
    imageView: ImageView,
    url: String?,
    placeholder: Drawable?
) {
    if (url == null) {
        imageView.setImageDrawable(placeholder)
    } else {
        Utils.loadImageWithGlide(imageView, url)
    }
}


@BindingAdapter("bind:bound_items")
fun setRecyclerViewResourceItems(
    recyclerView: RecyclerView,
    items: Resource<List<Blog>>?
) = setupBlogAdapter(recyclerView, items?.getData() ?: emptyList())


fun setupBlogAdapter(
    recyclerView: RecyclerView,
    items: List<Blog>
) {
    var adapter = (recyclerView.adapter as? BlogAdapter)

    if (adapter == null) {
        adapter = BlogAdapter()
        recyclerView.adapter = adapter
    }

    if (!items.isEmpty()) {
        adapter.submitList(items.toMutableList())
    }
}
