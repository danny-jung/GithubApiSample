package com.dannyjung.githubapi.presenter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.viewModel
import com.dannyjung.githubapi.presenter.databinding.ActivityMainBinding
import com.dannyjung.githubapi.presenter.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MavericksView {

    private lateinit var binding: ActivityMainBinding

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent?.data?.getQueryParameter(CODE)?.let { code ->
            loginViewModel.requestAccessToken(code)
        }
    }

    override fun invalidate() = Unit

    companion object {

        private const val CODE = "code"
    }
}
