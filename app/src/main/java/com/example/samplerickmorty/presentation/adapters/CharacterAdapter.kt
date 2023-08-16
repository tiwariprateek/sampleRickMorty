package com.example.samplerickmorty.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.samplerickmorty.data.CharacterResponse
import com.example.samplerickmorty.data.ResultsItem
import com.example.samplerickmorty.databinding.CharacterRowBinding

class CharacterAdapter(private val onNoteClicked: (ResultsItem) -> Unit) :
    ListAdapter<ResultsItem, CharacterAdapter.CharacterViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val note = getItem(position)
        note?.let {
            holder.bind(it)
        }
    }

    inner class CharacterViewHolder(private val binding: CharacterRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: ResultsItem) {
            binding.characterName.text = character.name
            Glide.with(itemView.context).load(character.image).into(binding.characterImage);
            binding.root.setOnClickListener {
                onNoteClicked(character)
            }
        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem == newItem
        }
    }
}