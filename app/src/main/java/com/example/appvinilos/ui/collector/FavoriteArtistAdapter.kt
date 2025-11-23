package com.example.appvinilos.ui.collector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appvinilos.databinding.FavoriteArtistItemBinding
import com.example.appvinilos.models.Performer

class FavoriteArtistAdapter(
    private val artists: List<Performer>,
    private val onArtistClicked: (Performer) -> Unit
) : RecyclerView.Adapter<FavoriteArtistAdapter.FavoriteArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteArtistViewHolder {
        val binding = FavoriteArtistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteArtistViewHolder, position: Int) {
        val artist = artists[position]
        holder.bind(artist)
        holder.itemView.setOnClickListener { onArtistClicked(artist) }
    }

    override fun getItemCount(): Int = artists.size

    class FavoriteArtistViewHolder(private val binding: FavoriteArtistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(performer: Performer) {
            binding.root.text = performer.name
        }
    }
}