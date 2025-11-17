package com.example.appvinilos.repositories

import com.example.appvinilos.models.Collector

interface CollectorRepository {
    suspend fun getCollectors(): List<Collector>
}
