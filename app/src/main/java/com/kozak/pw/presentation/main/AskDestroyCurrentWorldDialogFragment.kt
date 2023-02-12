package com.kozak.pw.presentation.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.kozak.pw.R

class AskDestroyCurrentWorldDialogFragment : DialogFragment() {

    companion object {
        const val TAG_NAME = "ask-drop-current-world-fragment"
    }

    /**
     * Use this instance of the interface to deliver action events
     */
    internal lateinit var listener: AskDestroyCurrentWorldDialogListener

    /**
     * The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it.
     */
    interface AskDestroyCurrentWorldDialogListener {
        fun onDestroyCurrentWorldClick()
    }

    /**
     * Override the Fragment.onAttach() method to instantiate the StartGameDialogListener
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as AskDestroyCurrentWorldDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException(("$context must implement NoticeDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.new_game)
                .setMessage(R.string.destroy_current_world_question)
                .setPositiveButton(R.string.button_lets_go) { _, _ ->
                    listener.onDestroyCurrentWorldClick()
                }
                .setNegativeButton(R.string.cancel) { _, _ ->
                    dismiss()
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}