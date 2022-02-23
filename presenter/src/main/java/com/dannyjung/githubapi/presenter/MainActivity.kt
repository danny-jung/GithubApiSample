package com.dannyjung.githubapi.presenter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.viewModel
import com.dannyjung.githubapi.presenter.databinding.ActivityMainBinding
import com.dannyjung.githubapi.presenter.ui.login.LoginViewModel
import com.dannyjung.githubapi.presenter.ui.mypage.MyPageFragment
import com.dannyjung.githubapi.presenter.ui.search.SearchFragment
import com.dannyjung.githubapi.presenter.ui.star.StarFragment
import com.dannyjung.githubapi.presenter.ui.star.StarViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MavericksView {

    private lateinit var binding: ActivityMainBinding

    private val loginViewModel: LoginViewModel by viewModel()
    private val starViewModel: StarViewModel by viewModel()

    private val fragments = mutableMapOf<MainTab, Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        changeFragment(MainTab.SEARCH)
        starViewModel.invalidate()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.data?.getQueryParameter(CODE)?.let { code ->
            loginViewModel.requestAccessToken(code)
        }
    }

    override fun invalidate() = Unit

    private fun initView() {
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val mainTab = getMainTab(menuItem.itemId)
            changeFragment(mainTab)

            true
        }
    }

    private fun changeFragment(mainTab: MainTab) {
        supportFragmentManager.commitNow {
            val fragment = fragments[mainTab]
            if (fragment == null) {
                fragments[mainTab] = createFragment(mainTab).also {
                    add(R.id.fragment_container_view, it)
                    show(it)
                }
            } else {
                show(fragment)
            }

            fragments
                .filterNot { it.key == mainTab }
                .forEach { (mainTab, _) ->
                    fragments[mainTab]?.let { hide(it) }
                }
        }
    }

    private fun createFragment(mainTab: MainTab): Fragment =
        when (mainTab) {
            MainTab.SEARCH -> SearchFragment.newInstance()
            MainTab.STAR -> StarFragment.newInstance()
            MainTab.MY_PAGE -> MyPageFragment.newInstance()
        }

    private fun getMainTab(menuItemId: Int): MainTab =
        MainTab.values().find { it.id == menuItemId }
            ?: throw IllegalArgumentException("Unknown menu_item_id : $menuItemId")

    enum class MainTab(val id: Int) {

        SEARCH(R.id.navigation_search),
        STAR(R.id.navigation_star),
        MY_PAGE(R.id.navigation_my_page)
    }

    companion object {

        private const val CODE = "code"
    }
}
