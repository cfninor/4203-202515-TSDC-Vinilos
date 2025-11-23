package com.example.appvinilos.models

data class Collector(
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val favoritePerformers: List<Performer>? = null,
    val collectorAlbums: List<CollectorAlbum>? = null
)
