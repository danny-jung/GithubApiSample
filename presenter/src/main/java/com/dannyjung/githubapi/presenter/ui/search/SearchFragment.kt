package com.dannyjung.githubapi.presenter.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.UniqueOnly
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.fragmentViewModel
import com.dannyjung.githubapi.domain.mapper.StarredRepoItemMapper
import com.dannyjung.githubapi.presenter.R
import com.dannyjung.githubapi.presenter.databinding.FragmentSearchBinding
import com.dannyjung.githubapi.presenter.ui.base.BaseFragment
import com.dannyjung.githubapi.presenter.ui.component.loadingProgressBar
import com.dannyjung.githubapi.presenter.ui.component.repoListItem
import com.dannyjung.githubapi.presenter.ui.component.space
import com.dannyjung.githubapi.presenter.ui.epoxy.simpleEpoxyController
import com.dannyjung.githubapi.presenter.ui.listener.addLoadMoreScrollListener
import com.dannyjung.githubapi.presenter.ui.star.StarViewModel
import com.dannyjung.githubapi.presenter.ui.utils.dpToPx
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val searchViewModel: SearchViewModel by fragmentViewModel()
    private val starViewModel: StarViewModel by activityViewModel()

    private val epoxyController by lazy { createEpoxyController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        searchViewModel.onEach(
            SearchState::searchRepoAsync,
            UniqueOnly("search_repo_async")
        ) { searchRepoAsync ->
            binding.progressBar.isVisible = searchRepoAsync is Loading
        }
    }

    private fun initView() {
        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { searchViewModel.setKeyword(it) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let { searchViewModel.setKeyword(it) }
                    return true
                }
            }
        )

        binding.epoxyRecyclerView.run {
            layoutManager = LinearLayoutManager(requireContext())
            clearOnScrollListeners()
            addLoadMoreScrollListener {
                searchViewModel.searchRepositoriesNextPage()
            }
            setController(epoxyController)
        }
    }

    override fun invalidate() = epoxyController.requestModelBuild()

    private fun createEpoxyController() = simpleEpoxyController(
        searchViewModel,
        starViewModel
    ) { searchState, starState ->
        searchState.searchRepo?.items?.forEach { item ->
            repoListItem {
                id("repo_list_item", item.id)
                repoItem(item)
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

        if (searchState.searchRepoNextPageAsync is Loading) {
            loadingProgressBar { id("loading_progress_bar") }
        }
    }

    companion object {

        fun newInstance() = SearchFragment()
    }
}
