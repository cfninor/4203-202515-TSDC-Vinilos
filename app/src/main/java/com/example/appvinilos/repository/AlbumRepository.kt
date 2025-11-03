package com.example.appvinilos.repository

import com.example.appvinilos.models.Album
import com.example.appvinilos.network.VinilosApiService

class AlbumRepository(private val apiService: VinilosApiService) {
    suspend fun getAlbums(): List<Album> {
        return apiService.getAlbums()
    }
}