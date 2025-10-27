package com.example.appvinilos

data class Track(
    val id: Int,
    val name: String,
    val duration: String
    // El 'album' se omite para evitar dependencias circulares en la definición
)