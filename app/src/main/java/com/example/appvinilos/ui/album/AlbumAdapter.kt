package com.example.appvinilos.ui.album

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appvinilos.R
import com.example.appvinilos.databinding.AddAlbumButtonItemBinding
import com.example.appvinilos.databinding.AlbumItemBinding
import com.example.appvinilos.models.Album
import java.text.SimpleDateFormat
import java.util.Locale

class AlbumAdapter(private var albums: List<Album>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ALBUM_ITEM_VIEW_TYPE = 0
        private const val BUTTON_ITEM_VIEW_TYPE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < albums.size) ALBUM_ITEM_VIEW_TYPE else BUTTON_ITEM_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ALBUM_ITEM_VIEW_TYPE -> {
                val binding = AlbumItemBinding.inflate(inflater, parent, false)
                AlbumViewHolder(binding)
            }
            else -> {
                val binding = AddAlbumButtonItemBinding.inflate(inflater, parent, false)
                ButtonViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AlbumViewHolder) {
            holder.bind(albums[position])
        }
    }

    override fun getItemCount(): Int = albums.size + 1

    @SuppressLint("NotifyDataSetChanged")
    fun updateAlbums(newAlbums: List<Album>) {
        albums = newAlbums
        notifyDataSetChanged()
    }

    class AlbumViewHolder(private val binding: AlbumItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(album: Album) {
            val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())
            val year = yearFormat.format(album.releaseDate)

            binding.albumName.text = album.name
            binding.albumYear.text = "($year)"
            binding.albumCover.load(album.cover) {
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_background)
            }

            itemView.setOnClickListener {
                val action = AlbumFragmentDirections.actionNavigationAlbumsToAlbumDetailFragment(album.id)
                itemView.findNavController().navigate(action)
            }
        }
    }

    class ButtonViewHolder(binding: AddAlbumButtonItemBinding) : RecyclerView.ViewHolder(binding.root)
}