package com.patinala.oxxodemo.adapters

import android.content.Context
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patinala.oxxodemo.R
import com.patinala.oxxodemo.common.Global
import com.patinala.oxxodemo.models.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_item_character_list.view.*

class CharacterListAdapter(val context: Context, var characters: List<Character>, val onClick: (character: Character) -> Unit): RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_character_list, parent, false)
        return CharacterListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        holder.bind(characters[position], onClick)
    }

    inner class CharacterListViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(character: Character, onClick: (character: Character) -> Unit) {

            Picasso.with(context)
                .load("${character.thumbnail.path}/landscape_incredible.${character.thumbnail.extension}")
                .into(view.character_list_image)
            view.character_list_name.text = character.name
            view.setOnClickListener { onClick(character) }

        }
    }

}