package com.example.hotcphotographerapp.Constants

import com.example.hotcphotographerapp.models.ModelFile

object Constants {

    object File {
        const val name = "name"
        const val image ="image"
        const val video ="video"
    }

    var photosFolderSelected: ModelFile.EventsPhotos? = null
}