package com.example.musicplayapp.domain.usecase

import com.example.musicplayapp.data.MusicRepository
import com.example.musicplayapp.domain.model.Song

class GetSongsUseCase(private val repository: MusicRepository) {
    fun invoke(): List<Song> = repository.getAllAudioFiles()
}