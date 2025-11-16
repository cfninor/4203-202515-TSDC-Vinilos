package com.example.appvinilos.repositories

import com.example.appvinilos.models.Album
import com.example.appvinilos.network.VinylsApiService

class AlbumRepositoryImpl(private val apiService: VinylsApiService) : AlbumRepository {
    override suspend fun getAlbums(): List<Album> {
        return apiService.getAlbums()
    }

    override suspend fun getAlbumDetail(albumId: Int): Album {
        return apiService.getAlbumDetail(albumId)
    }
}
