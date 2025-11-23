package com.example.appvinilos.repositories

import com.example.appvinilos.models.Album
import com.example.appvinilos.network.AlbumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlbumRepositoryImpl(private val apiService: AlbumService) : AlbumRepository {
    override suspend fun getAlbums(): List<Album> {
        return withContext(Dispatchers.IO) {
            apiService.getAlbums()
        }
    }

    override suspend fun getAlbumDetail(albumId: Int): Album {
        return withContext(Dispatchers.IO) {
            apiService.getAlbumDetail(albumId)
        }
    }
}
