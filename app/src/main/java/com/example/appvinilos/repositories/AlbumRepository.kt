package com.example.appvinilos.repositories

import com.example.appvinilos.models.Album
import com.example.appvinilos.models.Track

interface AlbumRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun getAlbumDetail(albumId: Int): Album
    suspend fun createAlbum(params: Map<String, String>): Album?
    suspend fun addTrackToAlbum(albumId: Int, params: Map<String, String>): Track?
}
