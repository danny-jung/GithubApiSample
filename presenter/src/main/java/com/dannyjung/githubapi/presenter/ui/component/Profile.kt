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
import com.dannyjung.githubapi.presenter.databinding.ViewProfileBinding
import com.dannyjung.githubapi.presenter.ui.utils.dpToPx

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class Profile @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewProfileBinding.inflate(LayoutInflater.from(context), this)

    init {
        setPadding(context.dpToPx(18))
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

    @TextProp
    fun setCompany(company: CharSequence?) {
        binding.company.text = company
    }

    @TextProp
    fun setLocation(location: CharSequence?) {
        binding.location.text = location
    }

    @TextProp
    fun setUrl(url: CharSequence?) {
        binding.url.text = url
    }

    @CallbackProp
    fun setOnLogoutButtonClick(onClick: (() -> Unit)?) {
        binding.logoutButton.setOnClickListener { onClick?.invoke() }
    }
}
