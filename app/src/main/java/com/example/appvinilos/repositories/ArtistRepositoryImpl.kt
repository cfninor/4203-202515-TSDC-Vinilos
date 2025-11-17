package com.example.appvinilos.repositories

import com.example.appvinilos.models.Performer
import com.example.appvinilos.network.VinylsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class ArtistRepositoryImpl(private val apiService: VinylsApiService) : ArtistRepository {
    override suspend fun getArtists(): List<Performer> {
        // Since we need to fetch from two different endpoints, we run them concurrently
        return withContext(Dispatchers.IO) {
            val bandsDeferred = async { apiService.getBands() }
            val musiciansDeferred = async { apiService.getMusicians() }

            val bands = bandsDeferred.await().map { it.apply { artistType = "band" } }
            val musicians = musiciansDeferred.await().map { it.apply { artistType = "musician" } }

            (bands + musicians).sortedBy { it.name }
        }
    }

    override suspend fun getBandDetail(bandId: Int): Performer {
        return apiService.getBandDetail(bandId)
    }

    override suspend fun getMusicianDetail(musicianId: Int): Performer {
        return apiService.getMusicianDetail(musicianId)
    }
}
