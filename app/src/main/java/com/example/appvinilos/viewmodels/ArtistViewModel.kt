package com.example.appvinilos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvinilos.models.Performer
import com.example.appvinilos.network.RetrofitClient
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ArtistViewModel : ViewModel() {

    private val _artists = MutableLiveData<List<Performer>>()
    val artists: LiveData<List<Performer>> = _artists

    private var allArtists: List<Performer> = emptyList()

    fun fetchArtists() {
        viewModelScope.launch {
            try {
                val bandsDeferred = async { RetrofitClient.instance.getBands() }
                val musiciansDeferred = async { RetrofitClient.instance.getMusicians() }

                val bands = bandsDeferred.await()
                val musicians = musiciansDeferred.await()

                val combinedArtists = (bands + musicians).sortedBy { it.name }
                allArtists = combinedArtists
                _artists.postValue(combinedArtists)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun searchArtists(query: String) {
        val filteredList = if (query.isEmpty()) {
            allArtists
        } else {
            allArtists.filter { it.name.contains(query, ignoreCase = true) }
        }
        _artists.postValue(filteredList)
    }
}