package com.example.hotcphotographerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Thumbnails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thumbnails)

        var backThumb = findViewById<ImageView>(R.id.thumbBack)
        var homeScreenThumb = findViewById<ImageView>(R.id.thumbHome)
        var vid1Thumb = findViewById<ImageView>(R.id.thumbVideo1)
        var vid2Thumb = findViewById<ImageView>(R.id.thumbVideo2)
        var vid3Thumb = findViewById<ImageView>(R.id.thumbVideo3)
        var vid4Thumb = findViewById<ImageView>(R.id.thumbVideo4)

        backThumb.setOnClickListener{
            this?.onBackPressed()
            finish()
        }

        homeScreenThumb.setOnClickListener{
            intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        vid1Thumb.setOnClickListener{
            intent = Intent( this, VideoScreens::class.java)
            startActivity(intent)
        }

        vid2Thumb.setOnClickListener{
            intent = Intent( this, VideoScreen2::class.java)
            startActivity(intent)
        }

        vid3Thumb.setOnClickListener{
            intent = Intent( this, VideoScreen3::class.java)
            startActivity(intent)
        }

        vid4Thumb.setOnClickListener{
            intent = Intent( this, VideoScreens::class.java)
            startActivity(intent)
        }
    }
}