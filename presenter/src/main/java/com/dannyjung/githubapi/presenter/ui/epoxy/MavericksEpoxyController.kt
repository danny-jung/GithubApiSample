package com.dannyjung.githubapi.presenter.ui.epoxy

import androidx.fragment.app.Fragment
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.withState

open class MavericksEpoxyController(
    val buildModelsCallback: EpoxyController.() -> Unit = {}
) : AsyncEpoxyController() {

    var onExceptionSwallowedHandler: ((RuntimeException) -> Unit)? = null

    override fun buildModels() {
        buildModelsCallback()
    }

    override fun onExceptionSwallowed(exception: RuntimeException) {
        super.onExceptionSwallowed(exception)

        onExceptionSwallowedHandler?.invoke(exception)
    }
}

fun <S : MavericksState, A : MavericksViewModel<S>> Fragment.simpleEpoxyController(
    viewModel: A,
    buildModels: EpoxyController.(state: S) -> Unit
) = MavericksEpoxyController {
    if (view == null || isRemoving) return@MavericksEpoxyController
    withState(viewModel) { state ->
        buildModels(state)
    }
}

fun <A : MavericksViewModel<B>,
        B : MavericksState,
        C : MavericksViewModel<D>,
        D : MavericksState> Fragment.simpleEpoxyController(
    viewModel1: A,
    viewModel2: C,
    buildModels: EpoxyController.(state1: B, state2: D) -> Unit
) = MavericksEpoxyController {
    if (view == null || isRemoving) return@MavericksEpoxyController
    withState(viewModel1, viewModel2) { state1, state2 ->
        buildModels(state1, state2)
    }
}
