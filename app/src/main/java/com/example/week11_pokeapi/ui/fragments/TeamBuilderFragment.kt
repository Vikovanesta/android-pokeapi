package com.example.week11_pokeapi.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week11_pokeapi.adapters.ItemTeamAdapter
import com.example.week11_pokeapi.database.PokeDatabase
import com.example.week11_pokeapi.database.TeamDao
import com.example.week11_pokeapi.databinding.FragmentTeamBuilderBinding
import com.example.week11_pokeapi.model.Team
import com.example.week11_pokeapi.ui.fragments.dialog.CreateTeamDialogFragment
import com.example.week11_pokeapi.ui.fragments.dialog.DeleteTeamDialogFragment
import java.util.concurrent.AbstractExecutorService
import java.util.concurrent.Executors

class TeamBuilderFragment : Fragment() {
    private var _binding: FragmentTeamBuilderBinding? = null
    private val binding get() = _binding!!
    private val teamAdapter = ItemTeamAdapter { team, action ->
        when (action) {
            "delete" -> {
                delete(team)
                getAllTeams()
            }
            "update" -> {
                update(team)
                getAllTeams()
            }
            "openNewActivity" -> Toast.makeText(requireContext(), "Yu klik on ${team.name}",
                Toast.LENGTH_SHORT).show()
            "openDeleteDialog" -> {
                val deleteTeamDialogFragment = DeleteTeamDialogFragment()
                deleteTeamDialogFragment.setTeamDeletionListener(object :
                    DeleteTeamDialogFragment.TeamDeletionListener {
                    override fun onTeamDeleted() {
                        delete(team)
                        getAllTeams()
                    }
                })
                deleteTeamDialogFragment.show(childFragmentManager, "delete team dialog")
            }
        }
    }
    private lateinit var mTeamDao: TeamDao
    private lateinit var executorService: AbstractExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamBuilderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        val db = PokeDatabase.getDatabase(requireContext())
        mTeamDao = db!!.teamDao()!!
        executorService = Executors.newSingleThreadExecutor() as AbstractExecutorService

        with(binding) {
            btnAddTeam.setOnClickListener {
                val createTeamDialogFragment = CreateTeamDialogFragment()
                createTeamDialogFragment.setTeamCreationListener(object :
                    CreateTeamDialogFragment.TeamCreationListener {
                    override fun onTeamCreated(name: String, description: String) {
                        val team = Team(name = name, description = description)
                        val id = insert(team)
                        getAllTeams()
                    }
                })
                createTeamDialogFragment.show(childFragmentManager, "create team dialog")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getAllTeams()
    }

    private fun getAllTeams() {
        mTeamDao.allTeams.observe(this) { teams ->
            Log.d("TeamBuilderFragment", "getAllTeams: $teams")
            teamAdapter.setTeamList(teams)
        }
    }

    private fun insert(team: Team) {
        executorService.execute { mTeamDao.insert(team) }
    }

    private fun update(team: Team) {
        Log.d("TeamBuilderFragment", "update: $team")
        executorService.execute { mTeamDao.update(team) }
    }

    private fun delete(team: Team) {
        executorService.execute { mTeamDao.delete(team) }
    }

    private fun setupRecyclerView() {
        binding.rvTeam.apply {
            adapter = teamAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}