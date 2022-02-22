package com.dannyjung.githubapi.presenter.ui.component

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.dannyjung.githubapi.presenter.R
import com.dannyjung.githubapi.presenter.ui.utils.dpToPx
import com.dannyjung.githubapi.presenter.ui.utils.getResourceIdWithAttr

class LoginButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        gravity = Gravity.CENTER
        text = "로그인"
        setTextColor(context.getResourceIdWithAttr(R.attr.colorOnSecondary))
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        setBackgroundResource(R.drawable.shape_rounded_rect_4)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(
            MeasureSpec.makeMeasureSpec(context.dpToPx(150), MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(context.dpToPx(50), MeasureSpec.EXACTLY)
        )
    }
}
