package com.dannyjung.githubapi.presenter.ui.search

import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.dannyjung.githubapi.data.di.qualifiers.IoDispatcher
import com.dannyjung.githubapi.domain.usecase.search.SearchRepositoriesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class SearchViewModel @AssistedInject constructor(
    @Assisted initialState: SearchState,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : MavericksViewModel<SearchState>(initialState) {

    private val searchSubject = MutableSharedFlow<String>()

    init {
        searchSubject
            .distinctUntilChangedBy { it }
            .debounce(300.toDuration(DurationUnit.MILLISECONDS))
            .mapLatest { keyword ->
                withContext(coroutineDispatcher) {
                    setState { copy(searchRepoAsync = Loading()) }

                    searchRepositoriesUseCase(keyword, 1)
                }
            }
            .flowOn(coroutineDispatcher)
            .catch { e ->
                setState { copy(searchRepoAsync = Fail(e)) }
            }
            .onEach { async ->
                setState {
                    copy(
                        searchRepoAsync = async,
                        searchRepo = async()
                    )
                }
            }
            .launchIn(viewModelScope)

        onEach(SearchState::keyword) { keyword ->
            keyword?.let { searchSubject.emit(it) }
        }
    }

    fun setKeyword(keyword: String) = setState { copy(keyword = keyword) }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<SearchViewModel, SearchState> {
        override fun create(state: SearchState): SearchViewModel
    }

    companion object :
        MavericksViewModelFactory<SearchViewModel, SearchState> by hiltMavericksViewModelFactory()
}
