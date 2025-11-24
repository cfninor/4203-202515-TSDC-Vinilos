package com.example.appvinilos.network

import com.example.appvinilos.models.Performer
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistService {
    @GET("bands")
    suspend fun getBands(): List<Performer>

    @GET("musicians")
    suspend fun getMusicians(): List<Performer>

    @GET("bands/{bandId}")
    suspend fun getBandDetail(@Path("bandId") bandId: Int): Performer

    @GET("musicians/{musicianId}")
    suspend fun getMusicianDetail(@Path("musicianId") musicianId: Int): Performer
}
