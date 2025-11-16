package com.example.appvinilos.repositories

import com.example.appvinilos.models.Collector
import com.example.appvinilos.network.VinylsApiService

class CollectorRepositoryImpl(private val apiService: VinylsApiService) : CollectorRepository {
    override suspend fun getCollectors(): List<Collector> {
        return apiService.getCollectors()
    }
}
