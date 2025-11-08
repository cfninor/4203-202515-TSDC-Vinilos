package com.example.appvinilos.ui.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appvinilos.R
import com.example.appvinilos.databinding.ArtistItemBinding
import com.example.appvinilos.models.Performer

class ArtistAdapter(private var artists: List<Performer>) : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding = ArtistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(artists[position])
    }

    override fun getItemCount(): Int = artists.size

    fun updateArtists(newArtists: List<Performer>) {
        artists = newArtists
        notifyDataSetChanged()
    }

    class ArtistViewHolder(private val binding: ArtistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(performer: Performer) {
            binding.artistName.text = performer.name
            binding.artistImage.load(performer.image) {
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_background)
            }
        }
    }
}