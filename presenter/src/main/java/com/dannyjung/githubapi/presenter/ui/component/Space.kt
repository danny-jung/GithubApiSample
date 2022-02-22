package com.dannyjung.githubapi.presenter.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.Px
import androidx.core.view.updateLayoutParams
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.dannyjung.githubapi.presenter.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class Space @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    @ModelProp
    fun setBackgroundColor(@ColorRes resId: Int?) {
        setBackgroundResource(resId ?: R.color.white)
    }

    @ModelProp
    fun setHeight(@Px height: Int) {
        if (this.height != height) {
            updateLayoutParams { this.height = height }
        }
    }
}
