package com.dannyjung.githubapi.presenter.ui.star

import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.fragmentViewModel
import com.dannyjung.githubapi.presenter.databinding.FragmentStarBinding
import com.dannyjung.githubapi.presenter.ui.base.BaseFragment
import com.dannyjung.githubapi.presenter.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarFragment : BaseFragment<FragmentStarBinding>(FragmentStarBinding::inflate) {

    private val starViewModel: StarViewModel by fragmentViewModel()
    private val loginViewModel: LoginViewModel by activityViewModel()

    override fun invalidate() = Unit
}
