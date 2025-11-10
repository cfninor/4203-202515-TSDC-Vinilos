package com.example.appvinilos.models

import com.example.appvinilos.models.Album
import java.util.Date

data class Performer(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    // Estos campos son para el detalle y pueden ser nulos
    val birthDate: Date? = null,
    val creationDate: Date? = null,
    val albums: List<Album>? = null,
    // Campo local para saber el tipo de artista
    var artistType: String? = null
)
