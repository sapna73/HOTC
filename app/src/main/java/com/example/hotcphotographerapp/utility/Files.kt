package com.example.hotcphotographerapp.utility

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.example.hotcphotographerapp.models.SharedStoragePhoto
import com.example.hotcphotographerapp.sdk29AndUp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

interface Files {
    fun onFileSelected(file: File)
}
fun getImagesFromFolder(context: Context, folder: String): List<File> {

    val selection = MediaStore.Images.Media.DATA + " LIKE ?"

    return queryUri(
        context,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        selection,
        arrayOf("%$folder/%")
    )
        .use { it?.getResultsFromCursor() ?: listOf() }
}



//suspend fun loadPhotosFromExternalStorage(): List<SharedStoragePhoto>{
//    return withContext(Dispatchers.IO){
//        val collection = sdk29AndUp {
//            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
//        } ?: MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//        val projection = arrayOf(
//            MediaStore.Images.Media._ID,
//            MediaStore.Images.Media.DISPLAY_NAME,
//            MediaStore.Images.Media.WIDTH,
//            MediaStore.Images.Media.HEIGHT
//        )
//        val photos = mutableListOf<SharedStoragePhoto>()
//        contentResolver.query(
//            collection as Uri,
//            projection,
//            null,
//            null,
//            "${MediaStore.Images.Media.DISPLAY_NAME} ASC"
//        )?.use { cursor ->
//            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
//            val displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
//            val widthColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
//            val heightColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)
//
//            while(cursor.moveToNext()){
//                val id = cursor.getLong(idColumn)
//                val displayName = cursor.getString(displayNameColumn)
//                val width = cursor.getInt(widthColumn)
//                val height = cursor.getInt(heightColumn)
//                val contentUri = ContentUris.withAppendedId(
//                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                    id
//                )
//                photos.add(SharedStoragePhoto(id, displayName, width, height, contentUri))
//            }
//            photos.toList()
//        } ?: listOf()
//    }
//
//}

private fun queryUri(
    context: Context,
    uri: Uri,
    selection: String?,
    selectionArgs: Array<String>?
): Cursor? {
    return context.contentResolver.query(
        uri,
        projection,
        null,
        null,
        "${MediaStore.Images.Media.DISPLAY_NAME} ASC"
    )
}

val projection = arrayOf(
    MediaStore.Images.Media._ID,
    MediaStore.Images.Media.DISPLAY_NAME,
    MediaStore.Images.Media.WIDTH,
    MediaStore.Images.Media.HEIGHT
)

private fun Cursor.getResultsFromCursor(): List<File> {
    val results = mutableListOf<File>()

    while (this.moveToNext()) {
        results.add(File(this.getString(this.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA))))
    }
    return results
}