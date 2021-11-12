package com.example.hotcphotographerapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.example.hotcphotographerapp.databinding.ActivityVideoScreensBinding

import android.os.Build

import android.os.Environment
import androidx.annotation.RequiresApi
import java.io.File


class VideoScreens : AppCompatActivity() {

    private lateinit var binding: ActivityVideoScreensBinding
    private val folderPath = File("/storage/emulated/0/HOTC")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoScreensBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaController = MediaController(this)
        binding.videoView.setVideoURI(Uri.parse(folderPath.toString()))
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.setZOrderOnTop(true)
        binding.videoView.start()
        setupVideoView()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun setupVideoView() {
        val path = "android.resource://" + packageName + "/" + R.raw.trail
        binding.videoView.setVideoURI(Uri.parse(path))
        binding.videoView.start()
    }
    @RequiresApi(Build.VERSION_CODES.R)
    fun openVideoPath() {
        Environment.getStorageDirectory().toString() + folderPath
        val file = File(
            Environment.getStorageDirectory(),
            "myFolder"
        ).toString() + folderPath
    }

}