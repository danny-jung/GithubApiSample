package com.dannyjung.githubapi.presenter.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelView
import com.dannyjung.githubapi.presenter.databinding.ViewLoadingProgressBarBinding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class LoadingProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        ViewLoadingProgressBarBinding.inflate(LayoutInflater.from(context), this)
    }
}
