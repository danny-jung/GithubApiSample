package com.dannyjung.githubapi.presenter.ui.listener

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addLoadMoreScrollListener(onLoadMore: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val range = recyclerView.computeVerticalScrollRange()
            val extent = recyclerView.computeVerticalScrollExtent()
            val offset = recyclerView.computeVerticalScrollOffset()

            if (range == 0 || extent == 0 || offset == 0) {
                return
            }

            val needToLoadMore = extent * 5 >= (range - (extent + offset))
            if (needToLoadMore) {
                onLoadMore()
            }
        }
    })
}
