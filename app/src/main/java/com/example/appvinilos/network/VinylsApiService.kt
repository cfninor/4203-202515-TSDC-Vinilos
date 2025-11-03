package com.example.appvinilos.network

import com.example.appvinilos.models.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface VinylsApiService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{albumId}")
    suspend fun getAlbumDetail(@Path("albumId") albumId: Int): Album
}
    
