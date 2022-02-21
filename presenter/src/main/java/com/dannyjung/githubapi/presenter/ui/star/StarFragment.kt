package com.dannyjung.githubapi.presenter.ui.star

import androidx.fragment.app.viewModels
import com.dannyjung.githubapi.presenter.databinding.FragmentStarBinding
import com.dannyjung.githubapi.presenter.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarFragment : BaseFragment<FragmentStarBinding>(FragmentStarBinding::inflate) {

    private val starViewModel by viewModels<StarViewModel>()
}
