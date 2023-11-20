package com.example.week11_pokeapi.ui.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.week11_pokeapi.databinding.FragmentCreateTeamDialogBinding

class CreateTeamDialogFragment : DialogFragment() {

    private var _binding: FragmentCreateTeamDialogBinding? = null
    private val binding get() = _binding!!
    private var teamCreationListener: TeamCreationListener? = null

    interface TeamCreationListener {
        fun onTeamCreated(name: String, description: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentCreateTeamDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnConfirm.setOnClickListener {
                val teamName = edtName.text.toString()
                val teamDescription = edtDescription.text.toString()

                if (teamName.isNotEmpty()) {
                    teamCreationListener?.onTeamCreated(teamName, teamDescription)
                    dismiss()
                } else {
                    edtName.error = "Team name cannot be empty"
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setTeamCreationListener(listener: TeamCreationListener) {
        teamCreationListener = listener
    }

    companion object {
        @JvmStatic fun newInstance() =
                CreateTeamDialogFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}