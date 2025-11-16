package com.example.appvinilos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvinilos.models.Album
import com.example.appvinilos.repositories.AlbumRepository
import kotlinx.coroutines.launch

class AlbumDetailViewModel(private val albumRepository: AlbumRepository) : ViewModel() {

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album> = _album

    fun fetchAlbumDetail(albumId: Int) {
        viewModelScope.launch {
            try {
                val albumDetail = albumRepository.getAlbumDetail(albumId)
                _album.postValue(albumDetail)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}