package com.dannyjung.githubapi.presenter.ui.mypage

import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.fragmentViewModel
import com.dannyjung.githubapi.presenter.databinding.FragmentMyPageBinding
import com.dannyjung.githubapi.presenter.ui.base.BaseFragment
import com.dannyjung.githubapi.presenter.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::inflate) {

    private val myPageViewModel: MyPageViewModel by fragmentViewModel()
    private val loginViewModel: LoginViewModel by activityViewModel()

    override fun invalidate() = Unit
}
