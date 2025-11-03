package com.example.appvinilos.ui.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appvinilos.R
import com.example.appvinilos.databinding.AddTrackButtonItemBinding
import com.example.appvinilos.databinding.TrackItemBinding
import com.example.appvinilos.models.Track

class TrackAdapter(private val tracks: List<Track>, private val albumCoverUrl: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TRACK_ITEM_VIEW_TYPE = 0
        private const val BUTTON_ITEM_VIEW_TYPE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < tracks.size) TRACK_ITEM_VIEW_TYPE else BUTTON_ITEM_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TRACK_ITEM_VIEW_TYPE -> {
                val binding = TrackItemBinding.inflate(inflater, parent, false)
                TrackViewHolder(binding)
            }
            else -> {
                val binding = AddTrackButtonItemBinding.inflate(inflater, parent, false)
                ButtonViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TrackViewHolder) {
            holder.bind(tracks[position], albumCoverUrl)
        }
        // No data to bind for the button ViewHolder
    }

    override fun getItemCount(): Int = tracks.size + 1 // Add 1 for the button

    class TrackViewHolder(private val binding: TrackItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Track, coverUrl: String) {
            binding.trackName.text = track.name
            binding.trackDuration.text = track.duration
            binding.trackAlbumCover.load(coverUrl) {
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_background)
            }
        }
    }

    class ButtonViewHolder(binding: AddTrackButtonItemBinding) : RecyclerView.ViewHolder(binding.root)
}