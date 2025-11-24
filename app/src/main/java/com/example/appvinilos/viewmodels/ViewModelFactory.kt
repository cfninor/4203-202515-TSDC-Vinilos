package com.example.appvinilos.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appvinilos.repositories.AlbumRepository
import com.example.appvinilos.repositories.ArtistRepository
import com.example.appvinilos.repositories.CollectorRepository

class ViewModelFactory(
    private val albumRepository: AlbumRepository,
    private val artistRepository: ArtistRepository,
    private val collectorRepository: CollectorRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AlbumViewModel::class.java) -> {
                AlbumViewModel(albumRepository) as T
            }
            modelClass.isAssignableFrom(ArtistViewModel::class.java) -> {
                ArtistViewModel(artistRepository) as T
            }
            modelClass.isAssignableFrom(CollectorViewModel::class.java) -> {
                CollectorViewModel(collectorRepository) as T
            }
            modelClass.isAssignableFrom(AlbumDetailViewModel::class.java) -> {
                AlbumDetailViewModel(albumRepository) as T
            }
            modelClass.isAssignableFrom(ArtistDetailViewModel::class.java) -> {
                ArtistDetailViewModel(artistRepository) as T
            }
            modelClass.isAssignableFrom(CollectorDetailViewModel::class.java) -> {
                CollectorDetailViewModel(collectorRepository, albumRepository) as T
            }
            modelClass.isAssignableFrom(CreateAlbumViewModel::class.java) -> {
                CreateAlbumViewModel(albumRepository) as T
            }
            modelClass.isAssignableFrom(CreateTrackViewModel::class.java) -> {
                CreateTrackViewModel(albumRepository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }
}