package com.dannyjung.githubapi.presenter.ui.star

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dannyjung.githubapi.presenter.databinding.FragmentStarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarFragment : Fragment() {

    private val starViewModel by viewModels<StarViewModel>()
    private var _binding: FragmentStarBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
