package com.android.space.presentation.blog.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.space.databinding.ListBlogBinding
import com.android.space.domain.model.Blog


class BlogAdapter :
    ListAdapter<Blog, BlogAdapter.BlogHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Blog>() {
        override fun areItemsTheSame(oldItem: Blog, newItem: Blog): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Blog, newItem: Blog): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.title == newItem.title &&
                    oldItem.summary == newItem.summary &&
                    oldItem.imgUrl == newItem.imgUrl &&
                    oldItem.publishedAt == newItem.publishedAt
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogHolder {
        return BlogHolder(
            androidx.databinding.DataBindingUtil.inflate(
                android.view.LayoutInflater.from(parent.context),
                com.android.space.R.layout.list_blog,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BlogAdapter.BlogHolder, position: Int) {
        holder.bind(getItem(position))

    }

    override fun getItemCount() = currentList.size


    inner class BlogHolder(private val listBlogBinding: ListBlogBinding) :
        RecyclerView.ViewHolder(listBlogBinding.root) {
        fun bind(blog: Blog) {
            listBlogBinding.blog = blog
            listBlogBinding.executePendingBindings()
        }
    }


}
