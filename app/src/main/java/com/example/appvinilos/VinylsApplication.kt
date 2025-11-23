package com.example.appvinilos

import android.app.Application
import com.example.appvinilos.network.AlbumService
import com.example.appvinilos.network.ArtistService
import com.example.appvinilos.network.CollectorService
import com.example.appvinilos.repositories.AlbumRepository
import com.example.appvinilos.repositories.AlbumRepositoryImpl
import com.example.appvinilos.repositories.ArtistRepository
import com.example.appvinilos.repositories.ArtistRepositoryImpl
import com.example.appvinilos.repositories.CollectorRepository
import com.example.appvinilos.repositories.CollectorRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VinylsApplication : Application() {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Create specific service instances
    private val albumService: AlbumService by lazy {
        retrofit.create(AlbumService::class.java)
    }

    private val artistService: ArtistService by lazy {
        retrofit.create(ArtistService::class.java)
    }

    private val collectorService: CollectorService by lazy {
        retrofit.create(CollectorService::class.java)
    }

    // Provide repositories with the correct service dependency
    val albumRepository: AlbumRepository by lazy {
        AlbumRepositoryImpl(albumService)
    }

    val artistRepository: ArtistRepository by lazy {
        ArtistRepositoryImpl(artistService)
    }

    val collectorRepository: CollectorRepository by lazy {
        CollectorRepositoryImpl(collectorService)
    }
}
