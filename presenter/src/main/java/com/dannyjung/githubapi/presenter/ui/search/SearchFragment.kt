package com.dannyjung.githubapi.presenter.ui.search

import androidx.fragment.app.viewModels
import com.dannyjung.githubapi.presenter.databinding.FragmentSearchBinding
import com.dannyjung.githubapi.presenter.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val searchViewModel by viewModels<SearchViewModel>()
}
