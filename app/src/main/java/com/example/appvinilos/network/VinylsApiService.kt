package com.example.appvinilos.network

import com.example.appvinilos.Album
import retrofit2.http.GET

interface VinylsApiService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>
}