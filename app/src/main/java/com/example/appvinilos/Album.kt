package com.example.appvinilos
import java.util.Date

enum class Genre{
    Classical,
    Salsa,
    Rock,
    Folk
}

enum class RecordLabel(val label: String) {
    SONY("Sony Music"),
    EMI("EMI"),
    FUENTES("Discos Fuentes"),
    ELEKTRA("Elektra"),
    FANIA("Fania Records")
}

data class Album(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: Date,
    val description: String,
    val genre: Genre,
    val recordLabel: RecordLabel,
    val performers: List<Performer> = emptyList(),
    val tracks: List<Track> = emptyList(),
    val comments: List<Comment> = emptyList()
)