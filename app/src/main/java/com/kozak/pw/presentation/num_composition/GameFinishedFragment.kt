package com.kozak.pw.presentation.num_composition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kozak.pw.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {

    private lateinit var binding: FragmentGameFinishedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }
}