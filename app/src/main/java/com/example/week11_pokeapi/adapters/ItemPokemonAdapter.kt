package com.example.week11_pokeapi.adapters

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week11_pokeapi.R
import com.example.week11_pokeapi.databinding.ItemPokemonBinding
import com.example.week11_pokeapi.model.Pokemon


typealias OnClickPokemon = (Pokemon) -> Unit
class ItemPokemonAdapter (private val onClickPokemon: OnClickPokemon) :
                          RecyclerView.Adapter<ItemPokemonAdapter.ItemPokemonViewHolder>() {

    private var pokemonList: List<Pokemon> = listOf()

    inner class ItemPokemonViewHolder (private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(pokemon: Pokemon) {
                binding.apply {
                    txtPokemonName.text = pokemon.name.replaceFirstChar(Char::titlecase)
                    txtPokemonId.text = "#${pokemon.id.toString().padStart(4, '0')}"

                    txtType1.text = pokemon.types[0].type.name.replaceFirstChar(Char::titlecase)
                    txtType1.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(itemView.context, getPokemonTypeColor(pokemon.types[0].type.name))
                    )

                    if (pokemon.types.size > 1) {
                        txtType2.visibility = android.view.View.VISIBLE
                        txtType2.text = pokemon.types[1].type.name.replaceFirstChar(Char::titlecase)
                        txtType2.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(itemView.context, getPokemonTypeColor(pokemon.types[1].type.name))
                        )
                    }

                    Glide.with(itemView.context)
                        .load(pokemon.sprites.other.officialArtwork.frontDefault)
                        .into(ivPokemon)

                    val bgDrawable = GradientDrawable(
                        GradientDrawable.Orientation.LEFT_RIGHT, intArrayOf(
                            ContextCompat.getColor(itemView.context, getPokemonTypeColor(pokemon.types[0].type.name)),
                            ContextCompat.getColor(itemView.context, R.color.grey)
                        )
                    )
                    bgDrawable.cornerRadius = 30f
                    bgDrawable.setStroke(2, ContextCompat.getColor(itemView.context, R.color.black))
                    root.background = bgDrawable

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
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: ItemPokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    fun setPokemonList(pokemonList: List<Pokemon>) {
        this.pokemonList = pokemonList
        notifyDataSetChanged()
    }

    private fun getPokemonTypeColor(type: String) : Int {
        val color =
            when (type) {
                "normal" -> R.color.normal
                "fire" -> R.color.fire
                "water" -> R.color.water
                "electric" -> R.color.electric
                "grass" -> R.color.grass
                "ice" -> R.color.ice
                "fighting" -> R.color.fighting
                "poison" -> R.color.poison
                "ground" -> R.color.ground
                "flying" -> R.color.flying
                "psychic" -> R.color.psychic
                "bug" -> R.color.bug
                "rock" -> R.color.rock
                "ghost" -> R.color.ghost
                "dragon" -> R.color.dragon
                "dark" -> R.color.dark
                "steel" -> R.color.steel
                "fairy" -> R.color.fairy
                else -> {
                    R.color.normal}
            }
        return color
    }
}