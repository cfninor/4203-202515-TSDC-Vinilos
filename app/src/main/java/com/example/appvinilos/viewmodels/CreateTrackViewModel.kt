package com.example.appvinilos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvinilos.repositories.AlbumRepository
import kotlinx.coroutines.launch

class CreateTrackViewModel(private val albumRepository: AlbumRepository) : ViewModel() {

    private val _creationStatus = MutableLiveData<Boolean>()
    val creationStatus: LiveData<Boolean> = _creationStatus

    fun addTrackToAlbum(albumId: Int, params: Map<String, String>) {
        viewModelScope.launch {
            try {
                val newTrack = albumRepository.addTrackToAlbum(albumId, params)
                _creationStatus.postValue(newTrack != null)
            } catch (e: Exception) {
                _creationStatus.postValue(false)
            }
        }
    }
}