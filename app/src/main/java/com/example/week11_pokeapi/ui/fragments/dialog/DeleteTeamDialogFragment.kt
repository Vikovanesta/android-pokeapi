package com.example.week11_pokeapi.ui.fragments.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.week11_pokeapi.databinding.FragmentCreateTeamDialogBinding

class DeleteTeamDialogFragment : DialogFragment() {

    private var teamDeletionListener: TeamDeletionListener? = null

    interface TeamDeletionListener {
        fun onTeamDeleted()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Are you sure you want to delete this team?")
                .setTitle("Delete Team")
                .setPositiveButton("Yes") { dialog, id ->
                    teamDeletionListener?.onTeamDeleted()
                }
                .setNegativeButton("No") { dialog, id ->
                    // User cancelled the dialog
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    fun setTeamDeletionListener(listener: TeamDeletionListener) {
        teamDeletionListener = listener
    }
}