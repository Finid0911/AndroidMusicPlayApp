package com.example.musicplayapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicplayapp.Greeting
import com.example.musicplayapp.R
import com.example.musicplayapp.domain.model.Song
import com.example.musicplayapp.presentation.ui.theme.MusicPlayAppTheme

@Composable
fun SongItem(
    song: Song,
    onClickSong: (song: Song) -> Unit,
    onMoreOption: (songId: Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .clickable { onClickSong(song) }
    ) {
        Row(
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(0.2f)
            ) {
                Image(
                    painter = if (song.thumbnail != null) {
                        BitmapPainter(song.thumbnail.asImageBitmap())
                    } else {
                        painterResource(id = R.drawable.enhypen_xo)
                    },
                    contentDescription = song.songTitle,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(14.dp)),
                )
                if (song.isSongPlayed) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(12.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .aspectRatio(1f)
                            .background(Color.Gray.copy(alpha = 0.5f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play Song icon",
                            modifier = Modifier
                                .size(30.dp)
                                .alpha(0.8f)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .weight(0.65f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CommonText(textContent = song.songTitle)
                CommonText(textContent = song.artist.uppercase(), color = Color.Gray)
            }

            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                modifier = Modifier
                    .weight(0.15f)
                    .clickable { onMoreOption(song.songId) }
            )
        }

        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.End)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val song =
        Song(
            songId = 0,
            songTitle = "XO (Only if you say yes)",
            thumbnail = null,
            artist = "Enhypen"
        )
    SongItem(
        song = song,
        onClickSong = {},
        onMoreOption = {}
    )
}
