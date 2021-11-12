package com.example.hotcphotographerapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.VideoView
import java.io.File

class SplashScreen : AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val videoView = findViewById<VideoView>(R.id.videoSplash)
        val path = "android.resource://" + packageName + "/" + R.raw.splash_video

        videoView.setVideoURI(Uri.parse(path))
        videoView.setZOrderOnTop(true)
        videoView.start()
        videoView.setOnCompletionListener {
            intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

//        handler = Handler()
//        handler.postDelayed({
//            intent = Intent( this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, 3000) // 3 seconds delay
    }
}