package com.example.appvinilos

import android.app.Application
import com.example.appvinilos.network.RetrofitClient
import com.example.appvinilos.repositories.AlbumRepository
import com.example.appvinilos.repositories.AlbumRepositoryImpl
import com.example.appvinilos.repositories.ArtistRepository
import com.example.appvinilos.repositories.ArtistRepositoryImpl
import com.example.appvinilos.repositories.CollectorRepository
import com.example.appvinilos.repositories.CollectorRepositoryImpl

class VinylsApplication : Application() {

    private val apiService by lazy {
        RetrofitClient.instance
    }

    val albumRepository: AlbumRepository by lazy {
        AlbumRepositoryImpl(apiService)
    }

    val artistRepository: ArtistRepository by lazy {
        ArtistRepositoryImpl(apiService)
    }

    val collectorRepository: CollectorRepository by lazy {
        CollectorRepositoryImpl(apiService)
    }
}