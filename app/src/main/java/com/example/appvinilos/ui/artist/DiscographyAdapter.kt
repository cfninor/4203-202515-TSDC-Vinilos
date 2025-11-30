package com.example.appvinilos.ui.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import com.example.appvinilos.R
import com.example.appvinilos.databinding.ArtistAlbumItemBinding
import com.example.appvinilos.models.Album

class DiscographyAdapter(
    private val albums: List<Album>,
    private val onAlbumClicked: (Album) -> Unit
) : RecyclerView.Adapter<DiscographyAdapter.DiscographyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscographyViewHolder {
        val binding = ArtistAlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscographyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscographyViewHolder, position: Int) {
        val album = albums[position]
        holder.bind(album)
        holder.itemView.setOnClickListener { onAlbumClicked(album) }
    }

    override fun getItemCount(): Int = albums.size

    class DiscographyViewHolder(private val binding: ArtistAlbumItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.albumName.text = album.name
            binding.albumCover.load(album.cover) {
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_background)
                size(ViewSizeResolver(binding.albumCover))
            }
        }
    }
}