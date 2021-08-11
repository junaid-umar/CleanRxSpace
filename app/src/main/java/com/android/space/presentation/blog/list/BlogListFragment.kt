package com.android.space.presentation.blog.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.space.databinding.FragmentBlogListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogListFragment : Fragment() {

    private val viewModel: BlogListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentBlogListBinding = FragmentBlogListBinding.inflate(
            inflater, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        return binding.root
    }

}