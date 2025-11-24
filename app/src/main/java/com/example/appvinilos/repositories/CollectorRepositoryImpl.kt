package com.example.appvinilos.repositories

import com.example.appvinilos.models.Collector
import com.example.appvinilos.network.CollectorService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CollectorRepositoryImpl(private val apiService: CollectorService) : CollectorRepository {
    override suspend fun getCollectors(): List<Collector> {
        return withContext(Dispatchers.IO) {
            apiService.getCollectors()
        }
    }

    override suspend fun getCollectorDetail(collectorId: Int): Collector {
        return withContext(Dispatchers.IO) {
            apiService.getCollectorDetail(collectorId)
        }
    }
}
