package com.test.rickmorty.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.test.rickmorty.R
import com.test.rickmorty.data.model.Character
import com.test.rickmorty.databinding.ItemCharacterBinding

class CharacterAdapter(private val rm: RequestManager) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var items = arrayListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(list: List<Character>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character) {
            rm.load(item.avatarUrl)
                .placeholder(R.color.black)
                .circleCrop()
                .into(binding.ivCharacterImage)

            binding.tvCharacterName.text = item.name
            binding.tvCharacterSpecies.text = item.species
            binding.tvCharacterStatus.text = "Status: ${item.status.uppercase()}"
        }
    }

}