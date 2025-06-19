package com.example.musicplayapp.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import androidx.annotation.RequiresApi
import com.example.musicplayapp.domain.model.Song
import com.example.musicplayapp.utils.APP_LOG_TAG
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class MusicRepository @Inject constructor(@ApplicationContext private val context: Context) {
    @RequiresApi(Build.VERSION_CODES.Q)
    fun getAllAudioFiles(): List<Song> {
        val songList = mutableListOf<Song>()

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.MIME_TYPE
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = "${MediaStore.Audio.Media.DATE_ADDED} DESC"

        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        Log.d(APP_LOG_TAG, "uri: $uri")

        context.contentResolver.query(uri, projection, selection, null, sortOrder)?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val mimeTypeColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.MIME_TYPE)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val title = cursor.getString(titleColumn)
                val artist = cursor.getString(artistColumn)
                val duration = cursor.getLong(durationColumn)
                val mimeType = cursor.getString(mimeTypeColumn)
                val thumbnail = getThumbnailFromUri(context, uri)
                val thumbnail1 = getRealPathFromUri(context, uri)
                Log.d(APP_LOG_TAG, "thumbnail: $thumbnail")
                Log.d(APP_LOG_TAG, "thumbnail1: $thumbnail1")

                // You can filter by MIME type here if needed
                if (mimeType == "audio/mpeg" || mimeType == "audio/flac") {
                    songList.add(Song(id, title, thumbnail, artist))
                }
            }
        }

        return songList
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun getThumbnailFromUri(context: Context, uri: Uri): Bitmap? {
        return try {
            val size = Size(200, 200)
            context.contentResolver.loadThumbnail(uri, size, null)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun loadThumbnail(absolutePath: String?): Bitmap? {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(absolutePath)
        val data = retriever.embeddedPicture
        retriever.release()
        return if (data == null) {
            null
        } else BitmapFactory.decodeByteArray(data, 0, data.size)
    }

    private fun getRealPathFromUri(context: Context, uri: Uri): String? {
        val projection = arrayOf(MediaStore.MediaColumns.DATA)
        context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            if (cursor.moveToFirst()) {
                return cursor.getString(columnIndex)
            }
        }
        return null
    }


}