package com.android.space.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.space.domain.usecases.SearchBlogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val searchBlogUseCase: SearchBlogUseCase
) : ViewModel() {

    fun searchBlogs(page: Int, query: String) {
        searchBlogUseCase.searchBlogs(10, page, query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { searchResult -> Log.d("TAG", " $searchResult") }
            )


    }
}
