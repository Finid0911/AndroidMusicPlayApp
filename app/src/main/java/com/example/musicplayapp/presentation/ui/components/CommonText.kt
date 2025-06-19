package com.example.musicplayapp.presentation.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CommonText(textContent: String, textSize: TextUnit = 14.sp, color: Color = Color.Black) {
    Text(text = textContent, fontSize = textSize, color = color)
}