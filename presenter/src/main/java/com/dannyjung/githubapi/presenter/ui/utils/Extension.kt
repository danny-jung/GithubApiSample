package com.dannyjung.githubapi.presenter.ui.utils

import android.content.Context
import android.util.TypedValue
import kotlin.math.floor

inline fun Context.dpToPx(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()

inline fun Context.getResourceIdWithAttr(attr: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.resourceId
}

inline fun Long.toAbbreviatedString(): String =
    when (this) {
        in 0..999 -> this.toString()
        in 1_000..999_999 -> {
            val abbreviatedValue = (floor(this / 100f) / 10).let {
                if (it % 1 == 0f) {
                    it.toLong()
                } else {
                    it
                }
            }
            "${abbreviatedValue}K"
        }
        else -> {
            val abbreviatedValue = (floor(this / 100_000.toDouble()) / 10).let {
                if (it % 1 == 0.toDouble()) {
                    it.toLong()
                } else {
                    it
                }
            }
            "${abbreviatedValue}M"
        }
    }
