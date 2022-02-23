package com.dannyjung.githubapi.presenter.ui.mypage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.UniqueOnly
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.fragmentViewModel
import com.dannyjung.githubapi.domain.mapper.StarredRepoItemMapper
import com.dannyjung.githubapi.presenter.R
import com.dannyjung.githubapi.presenter.databinding.FragmentMyPageBinding
import com.dannyjung.githubapi.presenter.ui.base.BaseFragment
import com.dannyjung.githubapi.presenter.ui.component.loadingProgressBar
import com.dannyjung.githubapi.presenter.ui.component.profile
import com.dannyjung.githubapi.presenter.ui.component.repoListItem
import com.dannyjung.githubapi.presenter.ui.component.space
import com.dannyjung.githubapi.presenter.ui.epoxy.simpleEpoxyController
import com.dannyjung.githubapi.presenter.ui.listener.addLoadMoreScrollListener
import com.dannyjung.githubapi.presenter.ui.login.LoginState
import com.dannyjung.githubapi.presenter.ui.login.LoginViewModel
import com.dannyjung.githubapi.presenter.ui.star.StarViewModel
import com.dannyjung.githubapi.presenter.ui.utils.dpToPx
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::inflate) {

    private val myPageViewModel: MyPageViewModel by fragmentViewModel()
    private val loginViewModel: LoginViewModel by activityViewModel()
    private val starViewModel: StarViewModel by activityViewModel()

    private val epoxyController by lazy { createEpoxyController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        loginViewModel.onEach(
            LoginState::accessToken,
            UniqueOnly("my_page_access_token")
        ) { accessToken ->
            binding.loginButton.isVisible = accessToken == null
            binding.epoxyRecyclerView.isVisible = accessToken != null

            if (accessToken != null) {
                myPageViewModel.initialize()
            }
        }

        binding.epoxyRecyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            clearOnScrollListeners()
            addLoadMoreScrollListener {
                myPageViewModel.getMyRepositoriesNextPage()
            }
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

    private fun createEpoxyController() = simpleEpoxyController(
        myPageViewModel,
        starViewModel
    ) { myPageState, starState ->
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
                repoItem(item)
                repoUrl(item.url)
                repoName(item.name)
                description(item.description)
                star(starState.allRepoIds?.contains(item.id) ?: false)
                stargazersCount(item.stargazersCount)
                watchersCount(item.watchersCount)
                ownerAvatarUrl(item.owner.avatarUrl)
                ownerName(item.owner.name)
                onStarClick { repoItem, isStar ->
                    val starredRepoItem = StarredRepoItemMapper.mapperToStarredRepoItem(repoItem)

                    if (isStar) {
                        starViewModel.deleteRepo(starredRepoItem)
                    } else {
                        starViewModel.addRepo(starredRepoItem)
                    }
                }
                onClick { repoUrl ->
                    startActivity(
                        Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(repoUrl)
                        }
                    )
                }
            }

            space {
                id("space", item.id)
                backgroundColor(R.color.gray_10)
                height(requireContext().dpToPx(8))
            }
        }

        if (myPageState.myRepoNextPageAsync is Loading) {
            loadingProgressBar { id("loading_progress_bar") }
        }
    }

    companion object {

        fun newInstance() = MyPageFragment()
    }
}
