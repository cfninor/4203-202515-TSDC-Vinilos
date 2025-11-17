package com.example.appvinilos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvinilos.models.Performer
import com.example.appvinilos.repositories.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistViewModel(private val artistRepository: ArtistRepository) : ViewModel() {

    private val _artists = MutableLiveData<List<Performer>>()
    val artists: LiveData<List<Performer>> = _artists

    private var allArtists: List<Performer> = emptyList()

    fun fetchArtists() {
        viewModelScope.launch {
            try {
                val combinedArtists = artistRepository.getArtists()
                allArtists = combinedArtists
                _artists.postValue(combinedArtists)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun searchArtists(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val filteredList = if (query.isEmpty()) {
                allArtists
            } else {
                allArtists.filter { it.name.contains(query, ignoreCase = true) }
            }
            withContext(Dispatchers.Main) {
                _artists.value = filteredList
            }
        }
    }
}