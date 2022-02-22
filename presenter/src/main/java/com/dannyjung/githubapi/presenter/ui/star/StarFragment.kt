package com.dannyjung.githubapi.presenter.ui.star

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.fragmentViewModel
import com.dannyjung.githubapi.presenter.databinding.FragmentStarBinding
import com.dannyjung.githubapi.presenter.ui.base.BaseFragment
import com.dannyjung.githubapi.presenter.ui.login.LoginState
import com.dannyjung.githubapi.presenter.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarFragment : BaseFragment<FragmentStarBinding>(FragmentStarBinding::inflate) {

    private val starViewModel: StarViewModel by fragmentViewModel()
    private val loginViewModel: LoginViewModel by activityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        loginViewModel.onEach(LoginState::accessToken) { accessToken ->
            binding.loginButton.isVisible = accessToken == null
        }
    }

    private fun initView() {
        binding.loginButton.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(loginViewModel.getAuthorizeUrl())
                }
            )
        }
    }

    override fun invalidate() = Unit
}
