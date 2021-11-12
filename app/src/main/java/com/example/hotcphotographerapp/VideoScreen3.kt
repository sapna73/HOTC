package com.example.hotcphotographerapp

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import androidx.annotation.RequiresApi
import com.example.hotcphotographerapp.databinding.ActivityVideoScreen2Binding
import com.example.hotcphotographerapp.databinding.ActivityVideoScreen3Binding
import java.io.File

class VideoScreen3 : AppCompatActivity() {
    private lateinit var binding: ActivityVideoScreen3Binding
    private val folderPath = File("/storage/emulated/0/HOTC")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoScreen3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaController = MediaController(this)
        binding.videoView2.setVideoURI(Uri.parse(folderPath.toString()))
        mediaController.setAnchorView(binding.videoView2)
        binding.videoView2.setMediaController(mediaController)
        binding.videoView2.setZOrderOnTop(true)
        binding.videoView2.start()
        setupVideoView()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun setupVideoView() {
        val path = "android.resource://" + packageName + "/" + R.raw.edit1
        binding.videoView2.setVideoURI(Uri.parse(path))
        binding.videoView2.start()
    }
}