package com.example.appvinilos.repositories

import com.example.appvinilos.models.Performer
import com.example.appvinilos.network.ArtistService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class ArtistRepositoryImpl(private val apiService: ArtistService) : ArtistRepository {
    override suspend fun getArtists(): List<Performer> {
        return withContext(Dispatchers.IO) {
            val bandsDeferred = async { apiService.getBands() }
            val musiciansDeferred = async { apiService.getMusicians() }

            val bands = bandsDeferred.await().map { it.apply { artistType = "band" } }
            val musicians = musiciansDeferred.await().map { it.apply { artistType = "musician" } }

            (bands + musicians).sortedBy { it.name }
        }
    }

    override suspend fun getBandDetail(bandId: Int): Performer {
        return withContext(Dispatchers.IO) {
            apiService.getBandDetail(bandId)
        }
    }

    override suspend fun getMusicianDetail(musicianId: Int): Performer {
        return withContext(Dispatchers.IO) {
            apiService.getMusicianDetail(musicianId)
        }
    }
}
