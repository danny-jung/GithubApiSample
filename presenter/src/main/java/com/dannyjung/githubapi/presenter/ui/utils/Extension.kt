package com.dannyjung.githubapi.presenter.ui.utils

import android.content.Context
import android.util.TypedValue

inline fun Context.dpToPx(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()

inline fun Context.getResourceIdWithAttr(attr: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.resourceId
}
