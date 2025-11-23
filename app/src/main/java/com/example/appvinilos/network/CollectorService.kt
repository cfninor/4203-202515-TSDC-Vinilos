package com.example.appvinilos.network

import com.example.appvinilos.models.Collector
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectorService {
    @GET("collectors")
    suspend fun getCollectors(): List<Collector>

    @GET("collectors/{collectorId}")
    suspend fun getCollectorDetail(@Path("collectorId") collectorId: Int): Collector
}
