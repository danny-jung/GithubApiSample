package com.dannyjung.githubapi.presenter.ui.mypage

import com.airbnb.mvrx.fragmentViewModel
import com.dannyjung.githubapi.presenter.databinding.FragmentMyPageBinding
import com.dannyjung.githubapi.presenter.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::inflate) {

    private val myPageViewModel: MyPageViewModel by fragmentViewModel()

    override fun invalidate() = Unit
}
