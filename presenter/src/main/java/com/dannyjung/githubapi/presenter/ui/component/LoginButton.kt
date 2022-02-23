package com.dannyjung.githubapi.presenter.ui.component

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.dannyjung.githubapi.presenter.R
import com.dannyjung.githubapi.presenter.ui.utils.dpToPx

class LoginButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        gravity = Gravity.CENTER
        text = "로그인"
        setPadding(context.dpToPx(34), context.dpToPx(16), context.dpToPx(34), context.dpToPx(16))
        setTextColor(ContextCompat.getColor(context, R.color.white))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        setBackgroundResource(R.drawable.shape_rounded_rect_4_color_primary_variant)
    }
}
