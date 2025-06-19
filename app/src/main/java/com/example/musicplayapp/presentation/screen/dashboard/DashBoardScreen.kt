package com.example.musicplayapp.presentation.screen.dashboard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.musicplayapp.domain.model.Song
import com.example.musicplayapp.presentation.ui.components.CommonText
import com.example.musicplayapp.presentation.ui.components.SongItem
import com.example.musicplayapp.utils.APP_LOG_TAG

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(viewModel: DashboardViewModel = hiltViewModel()) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        if (state.allowReadingStorage) {
            viewModel.fetchSongList()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { CommonText(textContent = "Compose Music App") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            LazyColumn {
                Log.d(APP_LOG_TAG, "songList = ${state.songList}")
                items(state.songList) { item ->
                    SongItem(
                        song = item,
                        onClickSong = { viewModel.onSongClicked(item) },
                        onMoreOption = {}
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val songList = listOf<Song>(
        Song(
            songId = 0,
            songTitle = "XO (Only if you say yes)",
            thumbnail = null,
            artist = "Enhypen"
        ),
        Song(songId = 1, songTitle = "Your eyes only", thumbnail = null, artist = "Enhypen"),
        Song(songId = 2, songTitle = "Royalty", thumbnail = null, artist = "Enhypen")
    )
//    DashBoardScreen(songList)
}