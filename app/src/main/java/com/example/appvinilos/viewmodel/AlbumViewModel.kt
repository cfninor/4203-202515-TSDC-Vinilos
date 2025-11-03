package com.example.appvinilos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvinilos.models.Album
import com.example.appvinilos.repository.AlbumRepository
import kotlinx.coroutines.launch

class AlbumViewModel(private val albumRepository: AlbumRepository) : ViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    private var allAlbums: List<Album> = emptyList()

    fun fetchAlbums() {
        viewModelScope.launch {
            try {
                val albumList = albumRepository.getAlbums()
                allAlbums = albumList
                _albums.postValue(albumList)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun searchAlbums(query: String) {
        val filteredList = if (query.isEmpty()) {
            allAlbums
        } else {
            allAlbums.filter { it.name.contains(query, ignoreCase = true) }
        }
        _albums.postValue(filteredList)
    }
}