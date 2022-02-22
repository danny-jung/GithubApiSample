package com.dannyjung.githubapi.presenter.ui.search

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.fragmentViewModel
import com.dannyjung.githubapi.presenter.databinding.FragmentSearchBinding
import com.dannyjung.githubapi.presenter.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val searchViewModel: SearchViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.a()
    }

    override fun invalidate() = Unit
}
