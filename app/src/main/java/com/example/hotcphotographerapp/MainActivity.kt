package com.example.hotcphotographerapp

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val couples = findViewById<TextView>(R.id.couplesNames)

        var btnPhotos = findViewById<ImageView>(R.id.btn_photos)
        var btnVideos = findViewById<ImageView>(R.id.btn_videos)

        val homeFolder = File("/storage/emulated/0/HOTC")
        val homeFile = homeFolder.listFiles()

        if (homeFile.isNotEmpty() && homeFile[0].isDirectory){
            couples.text = homeFile[0].name
        }
        val bgPhoto = File("/storage/emulated/0/HOTC/" + homeFile[0].name + "/background")

        val photoFolder = File("/storage/emulated/0/HOTC/" + homeFile[0].name + "/photos")

        val videoFolder = File("/storage/emulated/0/HOTC/" + homeFile[0].name + "/videos")

        if (photoFolder.exists()){
            btnPhotos.visibility = View.VISIBLE
        }else{
            btnPhotos.visibility = View.INVISIBLE
        }

        if (videoFolder.exists()){
            btnVideos.visibility = View.VISIBLE
        }else{
            btnVideos.visibility = View.INVISIBLE
        }


        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, Array(1){android.Manifest.permission.READ_EXTERNAL_STORAGE}, 121)
        }

        btnPhotos.setOnClickListener(){

            intent = Intent(this, Events::class.java)
            startActivity(intent)
        }

        btnVideos.setOnClickListener(){

            intent = Intent(this, VideoEvents::class.java)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 121 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            listImages()
    }

    private fun listImages() {
    }

    fun showHide(view: View) {
        if (view.getVisibility() === View.VISIBLE) {
            view.setVisibility(View.INVISIBLE)
        } else {
            view.setVisibility(View.VISIBLE)
        }
    }
}