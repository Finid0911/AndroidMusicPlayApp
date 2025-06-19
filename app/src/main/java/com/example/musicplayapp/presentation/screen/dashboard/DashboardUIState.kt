package com.example.musicplayapp.presentation.screen.dashboard

import com.example.musicplayapp.domain.model.Song

data class DashboardUIState(
    val allowReadingStorage: Boolean = true,
    val songList: List<Song> = emptyList()
)