package com.example.musicplayapp.domain.model

data class Song(
    val songId: Int,
    val songTitle: String,
    val thumbnail: String,
    val artist: String
)