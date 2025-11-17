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
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }
}