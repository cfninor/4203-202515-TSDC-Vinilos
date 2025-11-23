package com.example.appvinilos.repositories

import com.example.appvinilos.models.Album

interface AlbumRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun getAlbumDetail(albumId: Int): Album
    suspend fun createAlbum(params: Map<String, String>): Album?
}
