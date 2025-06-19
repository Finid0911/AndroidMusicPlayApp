package com.example.musicplayapp.domain.model

import android.graphics.Bitmap
import androidx.compose.runtime.Stable

@Stable
data class Song(
    val songId: Long,
    val songTitle: String,
    val thumbnail: Bitmap?,
    val artist: String,
    val isSongPlayed: Boolean = false
)