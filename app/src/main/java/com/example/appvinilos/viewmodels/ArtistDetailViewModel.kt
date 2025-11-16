package com.example.appvinilos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appvinilos.models.Performer
import com.example.appvinilos.repositories.ArtistRepository
import kotlinx.coroutines.launch

class ArtistDetailViewModel(private val artistRepository: ArtistRepository) : ViewModel() {

    private val _artist = MutableLiveData<Performer>()
    val artist: LiveData<Performer> = _artist

    fun fetchArtistDetail(artistId: Int, artistType: String) {
        viewModelScope.launch {
            try {
                val artistDetail = when (artistType) {
                    "band" -> artistRepository.getBandDetail(artistId)
                    "musician" -> artistRepository.getMusicianDetail(artistId)
                    else -> null
                }
                artistDetail?.let {
                    _artist.postValue(it)
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}