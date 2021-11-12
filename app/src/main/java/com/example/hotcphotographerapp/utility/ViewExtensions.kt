package com.example.hotcphotographerapp.utility

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.File
import java.lang.Math.log

fun ImageView.loadImage(file: File) {
    Glide.with(this)
        .load(file)
        .apply(
            RequestOptions()
                .fitCenter())
        .into(this)
}
fun handleError(throwable: Throwable?) {
    throwable?.let { log(throwable) }
}