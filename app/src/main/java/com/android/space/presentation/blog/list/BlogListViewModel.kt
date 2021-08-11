package com.android.space.presentation.blog.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.space.domain.model.Blog
import com.android.space.domain.model.Resource
import com.android.space.domain.usecases.SearchBlogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class BlogListViewModel
@Inject
constructor(
    searchBlogUseCase: SearchBlogUseCase
) : ViewModel() {

    private val query = "nasa"
    private var _blogs = MutableLiveData<Resource<List<Blog>>>()

    init {
        searchBlogUseCase.searchBlogs(
            10, 1, query
        ).subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _blogs.postValue(it)
            }
    }

    val blogs: LiveData<Resource<List<Blog>>>
        get() = _blogs

}