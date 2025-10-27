package com.example.appvinilos

data class Collector(val id: Int)

data class Comment(
    val id: Int,
    val description: String,
    val rating: Int,
    val collector: Collector? = null
)