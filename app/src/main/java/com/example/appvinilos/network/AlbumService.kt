package com.example.appvinilos.network

import com.example.appvinilos.models.Album
import com.example.appvinilos.models.Track
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{albumId}")
    suspend fun getAlbumDetail(@Path("albumId") albumId: Int): Album

    @POST("albums")
    suspend fun createAlbum(@Body params: Map<String, String>): Response<Album>

    @POST("albums/{id}/tracks")
    suspend fun addTrackToAlbum(@Path("id") albumId: Int, @Body params: Map<String, String>): Response<Track>
}
