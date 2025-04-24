package com.example.musicplayapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicplayapp.Greeting
import com.example.musicplayapp.R
import com.example.musicplayapp.presentation.ui.theme.MusicPlayAppTheme

@Composable
fun SongItem() {
    Row(
        horizontalArrangement = Arrangement.Absolute.Left,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { }
    ) {
        Image(
            painter = painterResource(id = R.drawable.enhypen_xo),
            contentDescription = "XO-Enhypen",
            modifier = Modifier
                .weight(0.3f)
                .padding(12.dp)
                .clip(RoundedCornerShape(18.dp))
                .aspectRatio(1f),
        )
        Column(
            modifier = Modifier.weight(0.55f)
        ) {
            CommonText(textContent = "XO (Only if you say yes)")
            CommonText(textContent = "Enhypen")
        }

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
            modifier = Modifier
                .weight(0.15f)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SongItem()
}
