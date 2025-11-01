package com.example.appvinilos.models

data class Comment(
    val id: Int,
    val description: String,
    val rating: Int,
    val collector: Collector? = null
)