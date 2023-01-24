package com.kozak.pw.presentation.num_composition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kozak.pw.R
import com.kozak.pw.databinding.FragmentGameBinding
import com.kozak.pw.domain.num_composition.entity.GameResult
import com.kozak.pw.domain.num_composition.entity.Level

/**
 * com.kozak.pw.presentation.num_composition.ChooseLevelFragment launches this fragment
 */
class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    private lateinit var level: Level

    companion object {
        val NAME: String = GameFragment::class.java.simpleName

        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    private fun parseArgs() {
        val args = requireArguments()
        if (!args.containsKey(KEY_LEVEL)) throw RuntimeException("Param 'level' is absent")
        @Suppress("DEPRECATION") // getParcelable(String, Class) available from Tiramisu
        args.getParcelable<Level>(KEY_LEVEL)?.let { level = it }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO remove
        binding.tvSum.setOnClickListener { launchGameFinishedFragment() }
    }

    private fun launchGameFinishedFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_container, GameFinishedFragment.newInstance(
                    GameResult(true, 0, 0, level.gameSettings)
                )
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}