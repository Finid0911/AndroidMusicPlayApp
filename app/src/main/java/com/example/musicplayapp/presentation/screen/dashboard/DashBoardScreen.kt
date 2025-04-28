package com.example.musicplayapp.presentation.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.overscroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.musicplayapp.domain.model.Song
import com.example.musicplayapp.presentation.ui.components.CommonText
import com.example.musicplayapp.presentation.ui.components.SongItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(songList: List<Song>) {
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
                items(songList) { item ->
                    SongItem(song = item, onClickSong = {}, onMoreOption = {}, isSongPlayed = false)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val songList = listOf<Song>(
        Song(songId = 0, songTitle = "XO (Only if you say yes)", thumbnail = "", artist = "Enhypen"),
        Song(songId = 1, songTitle = "Your eyes only", thumbnail = "", artist = "Enhypen"),
        Song(songId = 2, songTitle = "Royalty", thumbnail = "", artist = "Enhypen")
    )
    DashBoardScreen(songList)
}