package com.kozak.pw.presentation.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.kozak.pw.R
import com.kozak.pw.domain.game.GameSpeed

class StartGameDialogFragment : DialogFragment() {

    companion object {
        const val TAG_NAME = "start-game-fragment"
    }

    /**
     * Use this instance of the interface to deliver action events
     */
    internal lateinit var listener: StartGameDialogListener

    /**
     * The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it.
     */
    interface StartGameDialogListener {
        fun onStartNewGameClick(gameSpeedIndex: Int)
    }

    /**
     * Override the Fragment.onAttach() method to instantiate the StartGameDialogListener
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the StartGameDialogListener so we can send events to the host
            listener = context as StartGameDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException(("$context must implement StartGameDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val gameSpeeds = GameSpeed.values().map { speed ->
                speed.representation()
            }.toTypedArray()
            var selectedSpeed = GameSpeed.defaultSpeedIndex()

            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.select_speed)
                .setSingleChoiceItems(gameSpeeds, selectedSpeed) { _, id ->
                    selectedSpeed = id
                }
                .setPositiveButton(R.string.button_lets_go) { _, _ ->
                    listener.onStartNewGameClick(selectedSpeed)
                }
                .setNegativeButton(R.string.cancel) { _, _ ->
                    dismiss()
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}