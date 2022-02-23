package com.dannyjung.githubapi.presenter.ui.star

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.UniqueOnly
import com.airbnb.mvrx.activityViewModel
import com.dannyjung.githubapi.domain.mapper.RepoItemMapper
import com.dannyjung.githubapi.domain.mapper.StarredRepoItemMapper
import com.dannyjung.githubapi.presenter.R
import com.dannyjung.githubapi.presenter.databinding.FragmentStarBinding
import com.dannyjung.githubapi.presenter.ui.base.BaseFragment
import com.dannyjung.githubapi.presenter.ui.component.loadingProgressBar
import com.dannyjung.githubapi.presenter.ui.component.repoListItem
import com.dannyjung.githubapi.presenter.ui.component.space
import com.dannyjung.githubapi.presenter.ui.epoxy.simpleEpoxyController
import com.dannyjung.githubapi.presenter.ui.listener.addLoadMoreScrollListener
import com.dannyjung.githubapi.presenter.ui.login.LoginState
import com.dannyjung.githubapi.presenter.ui.login.LoginViewModel
import com.dannyjung.githubapi.presenter.ui.utils.dpToPx
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarFragment : BaseFragment<FragmentStarBinding>(FragmentStarBinding::inflate) {

    private val starViewModel: StarViewModel by activityViewModel()
    private val loginViewModel: LoginViewModel by activityViewModel()

    private val epoxyController by lazy { createEpoxyController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        loginViewModel.onEach(LoginState::accessToken, UniqueOnly("star_access_token")) { accessToken ->
            binding.loginButton.isVisible = accessToken == null
            binding.epoxyRecyclerView.isVisible = accessToken != null

            if (accessToken != null) {
                starViewModel.invalidate()
            }
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

        binding.epoxyRecyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            clearOnScrollListeners()
            addLoadMoreScrollListener {
                starViewModel.getStarredRepoNextPage()
            }
            setController(epoxyController)
        }
    }

    override fun invalidate() = epoxyController.requestModelBuild()

    private fun createEpoxyController() = simpleEpoxyController(starViewModel) { starState ->
        starState.repos?.forEach { item ->
            repoListItem {
                id("repo_list_item", item.id)
                repoItem(RepoItemMapper.mapperToRepoItem(item))
                repoUrl(item.url)
                repoName(item.name)
                description(item.description)
                star(starState.allStarCounts?.contains(item.id) ?: false)
                stargazersCount(
                    starState.allStarCounts?.get(item.id)
                        ?: item.stargazersCount
                )
                watchersCount(item.watchersCount)
                ownerAvatarUrl(item.owner.avatarUrl)
                ownerName(item.owner.name)
                onStarClick { repoItem, isStar ->
                    val starredRepoItem = StarredRepoItemMapper.mapperToStarredRepoItem(repoItem)

                    if (isStar) {
                        starViewModel.deleteRepo(starredRepoItem.id)
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

        if (starState.getReposNextPageAsync is Loading) {
            loadingProgressBar { id("loading_progress_bar") }
        }
    }

    companion object {

        fun newInstance() = StarFragment()
    }
}
