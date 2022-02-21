package com.dannyjung.githubapi.presenter.ui.star

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dannyjung.githubapi.presenter.databinding.FragmentStarBinding

class StarFragment : Fragment() {

    private lateinit var starViewModel: StarViewModel
    private var _binding: FragmentStarBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        starViewModel =
            ViewModelProvider(this).get(StarViewModel::class.java)

        _binding = FragmentStarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
