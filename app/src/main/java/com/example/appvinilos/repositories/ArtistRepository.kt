package com.example.appvinilos.repositories

import com.example.appvinilos.models.Performer

interface ArtistRepository {
    suspend fun getArtists(): List<Performer>
    suspend fun getBandDetail(bandId: Int): Performer
    suspend fun getMusicianDetail(musicianId: Int): Performer
}
