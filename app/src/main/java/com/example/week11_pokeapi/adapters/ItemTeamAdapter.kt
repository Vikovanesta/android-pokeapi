package com.example.week11_pokeapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week11_pokeapi.databinding.ItemTeamBinding
import com.example.week11_pokeapi.model.Pokemon
import com.example.week11_pokeapi.model.Team
import com.example.week11_pokeapi.model.TeamWithPokemon

typealias OnClickTeam = (Team) -> Unit
class ItemTeamAdapter (private val onClickTeam: OnClickTeam) :
                          RecyclerView.Adapter<ItemTeamAdapter.ItemTeamViewHolder>() {

    private var teamList: List<Team> = listOf()
    private var pokemonList: List<Pokemon> = listOf()

    inner class ItemTeamViewHolder (private val binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(team: Team) {
                binding.apply {
                    txtTeamName.text = team.name

//                    val imageView = ImageView(binding.root.context)
//                    imageView.layoutParams = ViewGroup.LayoutParams(
//                        60,
//                        60
//                    )
//                    imageView.apply {
//                        Glide.with(binding.root.context)
//                            .load(R.drawable.pokeball)
//                            .into(this)
//                    }

//                    imageGroup.addView(imageView)

                    root.setOnClickListener { onClickTeam(team) }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTeamViewHolder {
        val binding = ItemTeamBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ItemTeamViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: ItemTeamViewHolder, position: Int) {
        holder.bind(teamList[position])
    }

    fun setTeamList(teamList: List<Team>) {
        this.teamList = teamList
        notifyDataSetChanged()
    }
}