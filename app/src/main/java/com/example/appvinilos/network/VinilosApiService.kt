package com.example.appvinilos.network

import com.example.appvinilos.models.Album
import retrofit2.http.GET

interface VinilosApiService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>
}