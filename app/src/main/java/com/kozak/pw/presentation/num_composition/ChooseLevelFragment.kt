package com.kozak.pw.presentation.num_composition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kozak.pw.databinding.FragmentChooseLevelBinding
import com.kozak.pw.domain.num_composition.entity.Level

/**
 * com.kozak.pw.presentation.num_composition.WelcomeFragment launches this fragment
 */
class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonLevelTest.setOnClickListener { launchGameFragment(Level.TEST) }
            buttonLevelEasy.setOnClickListener { launchGameFragment(Level.EASY) }
            buttonLevelNormal.setOnClickListener { launchGameFragment(Level.NORMAL) }
            buttonLevelHard.setOnClickListener { launchGameFragment(Level.HARD) }
        }
    }

    private fun launchGameFragment(level: Level) {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}