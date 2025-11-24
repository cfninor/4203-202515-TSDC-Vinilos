package com.example.appvinilos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvinilos.models.Album
import com.example.appvinilos.models.Collector
import com.example.appvinilos.repositories.AlbumRepository
import com.example.appvinilos.repositories.CollectorRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class CollectorDetailViewModel(
    private val collectorRepository: CollectorRepository,
    private val albumRepository: AlbumRepository // We need this to fetch album details
) : ViewModel() {

    private val _collector = MutableLiveData<Collector>()
    val collector: LiveData<Collector> = _collector

    // New LiveData to hold the full album details
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    fun fetchCollectorDetail(collectorId: Int) {
        viewModelScope.launch {
            try {
                val collectorData = collectorRepository.getCollectorDetail(collectorId)
                _collector.postValue(collectorData)

                // After getting the collector, fetch the full details for each album
                collectorData.collectorAlbums?.let { collectorAlbums ->
                    val albumDetailsDeferred = collectorAlbums.map {
                        async { albumRepository.getAlbumDetail(it.id) }
                    }
                    val fullAlbumDetails = albumDetailsDeferred.awaitAll()
                    _albums.postValue(fullAlbumDetails)
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}