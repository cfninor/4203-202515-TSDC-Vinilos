package com.example.appvinilos.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvinilos.models.Album
import com.example.appvinilos.repositories.AlbumRepository
import kotlinx.coroutines.launch

class CreateAlbumViewModel(private val albumRepository: AlbumRepository) : ViewModel() {

    // LiveData to observe the result of the creation
    private val _creationStatus = MutableLiveData<Boolean>()
    val creationStatus: LiveData<Boolean> = _creationStatus

    fun createAlbum(params: Map<String, String>) {
        viewModelScope.launch {
            try {
                val newAlbum = albumRepository.createAlbum(params)
                _creationStatus.postValue(newAlbum != null)
            } catch (e: Exception) {
                Log.e("CreateAlbumViewModel", "Error creating album: ${e.message}")
                e.printStackTrace()
                _creationStatus.postValue(false)
            }
        }
    }
}