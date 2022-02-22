package com.dannyjung.githubapi.presenter.ui.star

import com.airbnb.mvrx.fragmentViewModel
import com.dannyjung.githubapi.presenter.databinding.FragmentStarBinding
import com.dannyjung.githubapi.presenter.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarFragment : BaseFragment<FragmentStarBinding>(FragmentStarBinding::inflate) {

    private val starViewModel: StarViewModel by fragmentViewModel()

    override fun invalidate() = Unit
}
