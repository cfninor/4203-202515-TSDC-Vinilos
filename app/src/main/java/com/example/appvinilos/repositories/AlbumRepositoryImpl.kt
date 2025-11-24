package com.example.appvinilos.repositories

import android.util.Log
import com.example.appvinilos.models.Album
import com.example.appvinilos.models.Track
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

    override suspend fun createAlbum(params: Map<String, String>): Album? {
        return withContext(Dispatchers.IO) {
            val response = apiService.createAlbum(params)
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("AlbumRepository", "Error creating album: ${response.errorBody()?.string()}")
                null
            }
        }
    }

    override suspend fun addTrackToAlbum(albumId: Int, params: Map<String, String>): Track? {
        return withContext(Dispatchers.IO) {
            val response = apiService.addTrackToAlbum(albumId, params)
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("AlbumRepository", "Error adding track: ${response.errorBody()?.string()}")
                null
            }
        }
    }
}
