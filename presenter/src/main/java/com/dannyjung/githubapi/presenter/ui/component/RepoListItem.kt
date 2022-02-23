package com.dannyjung.githubapi.presenter.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.bumptech.glide.Glide
import com.dannyjung.githubapi.presenter.R
import com.dannyjung.githubapi.presenter.databinding.ViewRepoListItemBinding
import com.dannyjung.githubapi.presenter.ui.utils.dpToPx
import com.dannyjung.githubapi.presenter.ui.utils.toAbbreviatedString

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class RepoListItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewRepoListItemBinding.inflate(LayoutInflater.from(context), this)

    init {
        setPadding(context.dpToPx(12))
        clipToPadding = false
    }

    var repoId: Long? = null
        @ModelProp set

    var repoUrl: String? = null
        @ModelProp set

    @TextProp
    fun setRepoName(repoName: CharSequence?) {
        binding.repoName.text = repoName
    }

    @TextProp
    fun setDescription(description: CharSequence?) {
        binding.description.text = description
    }

    @ModelProp
    fun setStar(isStar: Boolean?) {
        binding.stargazers.setCompoundDrawablesRelativeWithIntrinsicBounds(
            if (isStar == true) {
                R.drawable.ic_star_14dp
            } else {
                R.drawable.ic_star_stroke_14dp
            },
            0,
            0,
            0
        )
    }

    @ModelProp
    fun setStargazersCount(stargazersCount: Long?) {
        binding.stargazers.text = stargazersCount?.toAbbreviatedString()
    }

    @ModelProp
    fun setWatchersCount(watchersCount: Long?) {
        binding.watchers.text = watchersCount?.toAbbreviatedString()
    }

    @ModelProp
    fun setOwnerAvatarUrl(ownerAvatarUrl: String?) {
        Glide.with(this)
            .load(ownerAvatarUrl)
            .placeholder(R.drawable.shape_oval_gray_10)
            .circleCrop()
            .into(binding.ownerAvatar)
    }

    @TextProp
    fun setOwnerName(ownerName: CharSequence?) {
        binding.ownerName.text = ownerName
    }

    @CallbackProp
    fun setOnStarClick(onClick: ((id: Long) -> Unit)?) {
        binding.stargazers.setOnClickListener { repoId?.let { onClick?.invoke(it) } }
    }

    @CallbackProp
    fun setOnClick(onClick: ((repoUrl: String) -> Unit)?) {
        setOnClickListener { repoUrl?.let { onClick?.invoke(it) } }
    }
}
