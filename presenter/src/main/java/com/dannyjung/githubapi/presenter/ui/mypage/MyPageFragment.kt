package com.dannyjung.githubapi.presenter.ui.mypage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.fragmentViewModel
import com.dannyjung.githubapi.presenter.R
import com.dannyjung.githubapi.presenter.databinding.FragmentMyPageBinding
import com.dannyjung.githubapi.presenter.ui.base.BaseFragment
import com.dannyjung.githubapi.presenter.ui.component.profile
import com.dannyjung.githubapi.presenter.ui.component.repoListItem
import com.dannyjung.githubapi.presenter.ui.component.space
import com.dannyjung.githubapi.presenter.ui.epoxy.simpleEpoxyController
import com.dannyjung.githubapi.presenter.ui.login.LoginState
import com.dannyjung.githubapi.presenter.ui.login.LoginViewModel
import com.dannyjung.githubapi.presenter.ui.utils.dpToPx
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::inflate) {

    private val myPageViewModel: MyPageViewModel by fragmentViewModel()
    private val loginViewModel: LoginViewModel by activityViewModel()

    private val epoxyController by lazy { createEpoxyController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        loginViewModel.onEach(LoginState::accessToken) { accessToken ->
            binding.loginButton.isVisible = accessToken == null
            binding.epoxyRecyclerView.isVisible = accessToken != null

            if (accessToken != null) {
                myPageViewModel.initialize()
            }
        }

        binding.epoxyRecyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            setController(epoxyController)
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

    override fun invalidate() = epoxyController.requestModelBuild()

    private fun createEpoxyController() = simpleEpoxyController(myPageViewModel) { myPageState ->
        myPageState.user?.let { user ->
            profile {
                id("profile")
                ownerAvatarUrl(user.avatarUrl)
                ownerName(user.name)
                company(user.company)
                location(user.location)
                url(user.url)
                onLogoutButtonClick {
                    loginViewModel.clearAccessToken()
                    myPageViewModel.clear()
                }
            }

            space {
                id("space")
                backgroundColor(R.color.gray_10)
                height(requireContext().dpToPx(8))
            }
        }

        myPageState.userRepos?.forEach { item ->
            repoListItem {
                id("repo_list_item", item.id)
                repoId(item.id)
                repoName(item.name)
                description(item.description)
                stargazersCount(item.stargazersCount)
                watchersCount(item.watchersCount)
                ownerAvatarUrl(item.owner.avatarUrl)
                ownerName(item.owner.name)
                onStarClick { repoId ->
                    // TODO
                }
            }

            space {
                id("space", item.id)
                backgroundColor(R.color.gray_10)
                height(requireContext().dpToPx(8))
            }
        }
    }
}
