package com.example.musicplayapp.presentation.screen.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayapp.data.MusicRepository
import com.example.musicplayapp.domain.model.Song
import com.example.musicplayapp.domain.usecase.GetSongsUseCase
import com.example.musicplayapp.utils.APP_LOG_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: MusicRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUIState())
    val uiState: StateFlow<DashboardUIState> = _uiState.asStateFlow()

    fun fetchSongList() {
        viewModelScope.launch(Dispatchers.IO) {
            val songs = repository.getAllAudioFiles()
            Log.d(APP_LOG_TAG, "songs = $songs")
            withContext(Dispatchers.Main) {
                _uiState.update { uiState ->
                    uiState.copy(
                        songList = songs
                    )
                }
            }
        }
    }

    fun onSongClicked(song: Song) {
        viewModelScope.launch {
            _uiState.update { uiState ->
                val updatedList = uiState.songList.map {
                    if (it.songId == song.songId) {
                        song.copy(isSongPlayed = true)
                    } else {
                        it.copy(isSongPlayed = false)
                    }
                }
                uiState.copy(songList = updatedList)
            }
        }
    }
}