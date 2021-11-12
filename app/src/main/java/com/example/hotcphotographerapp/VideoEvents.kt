package com.example.hotcphotographerapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.hotcphotographerapp.adapter.VideosAdapter

class VideoEvents : AppCompatActivity() {

//    private lateinit var binding: ActivityVideoEventsBinding
//    private val compositeDisposable = CompositeDisposable()
    private val videosAdapter = VideosAdapter()
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_events)

//        binding = ActivityVideoEventsBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        var back = findViewById<ImageView>(R.id.imageViewBackVideo)
        var homeScreen = findViewById<ImageView>(R.id.imageViewVideoHome)
        var vid1 = findViewById<ImageView>(R.id.cdVideo1)
        var vid2 = findViewById<ImageView>(R.id.cdVideo2)
        var vid3 = findViewById<ImageView>(R.id.cdVideo3)
        var vid4 = findViewById<ImageView>(R.id.cdVideo4)

        back.setOnClickListener {
            Toast.makeText(this, "msg,........", Toast.LENGTH_SHORT).show()
            intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
        }
        homeScreen.setOnClickListener{
            Toast.makeText(this, "home,........", Toast.LENGTH_SHORT).show()
            intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        vid1.setOnClickListener{
            intent = Intent( this, Thumbnails::class.java)
            startActivity(intent)
        }
        vid2.setOnClickListener{
            intent = Intent( this, Thumbnails::class.java)
            startActivity(intent)
        }
        vid3.setOnClickListener{
            intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
        }
        vid4.setOnClickListener{
            intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}