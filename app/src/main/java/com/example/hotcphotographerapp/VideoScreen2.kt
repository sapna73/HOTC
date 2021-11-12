package com.example.hotcphotographerapp

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import androidx.annotation.RequiresApi
import com.example.hotcphotographerapp.databinding.ActivityVideoScreen2Binding
import java.io.File

class VideoScreen2 : AppCompatActivity() {
    private lateinit var binding: ActivityVideoScreen2Binding
    private val folderPath = File("/storage/emulated/0/HOTC")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaController = MediaController(this)
        binding.videoView1.setVideoURI(Uri.parse(folderPath.toString()))
        mediaController.setAnchorView(binding.videoView1)
        binding.videoView1.setMediaController(mediaController)
        binding.videoView1.setZOrderOnTop(true)
        binding.videoView1.start()
        setupVideoView()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun setupVideoView() {
        val path = "android.resource://" + packageName + "/" + R.raw.trail2
        binding.videoView1.setVideoURI(Uri.parse(path))
        binding.videoView1.start()
    }
}