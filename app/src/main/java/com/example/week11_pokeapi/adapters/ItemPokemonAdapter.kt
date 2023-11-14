package com.example.week11_pokeapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week11_pokeapi.databinding.ItemPokemonBinding
import com.example.week11_pokeapi.model.Pokemon

typealias OnClickPokemon = (Pokemon) -> Unit
class ItemPokemonAdapter (private val onClickPokemon: OnClickPokemon) :
                          RecyclerView.Adapter<ItemPokemonAdapter.ItemPokemonViewHolder>() {

    private var listPokemon: List<Pokemon> = listOf()

    inner class ItemPokemonViewHolder (private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(pokemon: Pokemon) {
                binding.apply {
                    txtPokemonName.text = pokemon.name
    //                Glide.with(itemView.context)
    //                    .load(pokemon.sprites.frontDefault)
    //                    .into(ivPokemon)

                    root.setOnClickListener { onClickPokemon(pokemon) }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ItemPokemonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listPokemon.size
    }

    override fun onBindViewHolder(holder: ItemPokemonViewHolder, position: Int) {
        holder.bind(listPokemon[position])
    }

    fun setPokemonList(pokemonList: List<Pokemon>) {
        listPokemon = pokemonList
        notifyDataSetChanged()
    }
}