package com.example.week11_pokeapi.adapters

import android.content.Context
import android.text.InputType
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week11_pokeapi.api.PokeApiClient
import com.example.week11_pokeapi.databinding.ItemTeamBinding
import com.example.week11_pokeapi.model.Pokemon
import com.example.week11_pokeapi.model.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext
import kotlin.math.roundToInt

typealias OnClickTeam = (Team, String) -> Unit
class ItemTeamAdapter (private val onClickTeam: OnClickTeam) :
                          RecyclerView.Adapter<ItemTeamAdapter.ItemTeamViewHolder>() {

    private var teamList: List<Team> = listOf()
    val pokeApiClient = PokeApiClient.getInstance()
    val pokemonList = mutableListOf<Pokemon>()
    val randomPokemonIds = (1..800).shuffled().take(6)

    inner class ItemTeamViewHolder (private val binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(team: Team) {
                binding.apply {
                    txtTeamName.text = team.name

                    txtTeamName.setOnClickListener {
                        viewTextToEditText(txtTeamName, team)
                    }

                    btnDelete.setOnClickListener { onClickTeam(team, "delete") }

                    for (id in randomPokemonIds) {
                        CoroutineScope(Dispatchers.IO).launch {
                            val pokemon = pokeApiClient.getPokemon(id)
                            if (pokemon.isSuccessful) {
                                pokemonList.add(pokemon.body()!!)
                                withContext(Dispatchers.Main) {
                                    val imageView = ImageView(binding.root.context)
                                    imageView.layoutParams = ViewGroup.LayoutParams(
                                        dpToPx(binding.root.context, 60),
                                        dpToPx(binding.root.context, 60)
                                    )
                                    imageView.apply {
                                        Glide.with(binding.root.context)
                                            .load(pokemon.body()!!.sprites.other.officialArtwork.frontDefault)
                                            .into(this)
                                    }
                                    imageGroup.addView(imageView)
                                }
                            }
                        }
                    }


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

                    root.setOnClickListener { onClickTeam(team, "openNewActivity") }
                }
            }
        }

    private fun viewTextToEditText(txtTeamName: TextView, team: Team) {
        val textStyle = txtTeamName.typeface
        val textColors = txtTeamName.textColors

        val editText = EditText(txtTeamName.context)
        editText.text = SpannableStringBuilder(txtTeamName.text)
        editText.id = txtTeamName.id
        editText.setSelection(editText.text.length)

//        editText.typeface = textStyle
//        editText.setTextColor(textColors)
        editText.inputType = InputType.TYPE_CLASS_TEXT

        val parent = txtTeamName.parent as ViewGroup
        val index = parent.indexOfChild(txtTeamName)
        parent.removeViewAt(index)
        parent.addView(editText, index, txtTeamName.layoutParams)
        editText.requestFocus()
        editText.imeOptions = EditorInfo.IME_ACTION_DONE

        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val newTeamName = editText.text.toString()
                val newTeam = Team(team.id, newTeamName, team.description)
                Log.d("TeamAdapter", "newTeam: $newTeam")
                onClickTeam(newTeam, "update")
                editTextToViewText(editText, txtTeamName)
                true
            } else {
                false
            }
        }
        
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                editTextToViewText(editText, txtTeamName)
            }
        }
    }

    private fun editTextToViewText(editText: EditText, txtTeamName: TextView) {
        val newText = editText.text.toString()
        txtTeamName.text = newText

        val imm = editText.context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)

        val parent = editText.parent as ViewGroup
        val index = parent.indexOfChild(editText)
        parent.removeViewAt(index)
        parent.addView(txtTeamName, index, editText.layoutParams)
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

    fun dpToPx(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp.toFloat() * density).roundToInt()
    }
}