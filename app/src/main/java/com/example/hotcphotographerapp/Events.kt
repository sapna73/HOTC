package com.example.hotcphotographerapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotcphotographerapp.Constants.Constants
import com.example.hotcphotographerapp.adapter.EventsAdapter
import com.example.hotcphotographerapp.models.ModelFile
import com.example.hotcphotographerapp.utility.Files
import com.google.android.material.imageview.ShapeableImageView
import java.io.File

class Events() : AppCompatActivity(), Files {
//    lateinit var recyclerView : RecyclerView
//    private val eventsAdapter: EventsAdapter = TODO()

    private lateinit var sharedPreferences: SharedPreferences
    private var path = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

//        recyclerView  = findViewById<RecyclerView>(R.id.rc)

        var backPhotos = findViewById<ImageView>(R.id.imageViewBackPhotos)
        var homeScreen = findViewById<ImageView>(R.id.imageViewHome)
        var card1 = findViewById<ShapeableImageView>(R.id.cd1)
        var card2 = findViewById<ShapeableImageView>(R.id.cd2)
        var card3 = findViewById<ShapeableImageView>(R.id.cd3)
        var card4 = findViewById<ShapeableImageView>(R.id.cd4)
        var card5 = findViewById<ShapeableImageView>(R.id.cd5)

        card1.setOnClickListener{
            intent = Intent(this, PhotosDisplay::class.java)
            startActivity(intent)
        }
        card2.setOnClickListener{
            intent = Intent(this, PhotosDisplay::class.java)
            startActivity(intent)
        }
        card3.setOnClickListener{
            intent = Intent(this, PhotosDisplay::class.java)
            startActivity(intent)
        }
        card3.setOnClickListener{
            intent = Intent(this, PhotosDisplay::class.java)
            startActivity(intent)
        }
        card4.setOnClickListener{
            intent = Intent(this, PhotosDisplay::class.java)
            startActivity(intent)
        }
        card5.setOnClickListener{
            intent = Intent(this, PhotosDisplay::class.java)
            startActivity(intent)
        }

        val homeFolder = File("/storage/emulated/0/HOTC")
        val homeFile = homeFolder.listFiles()

        //setting recycler to horizontal scroll
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        backPhotos.setOnClickListener {
            this?.onBackPressed()
            finish()
        }

        homeScreen.setOnClickListener{
            intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        //creating a  arraylist of data
        val data: ArrayList<String> = ArrayList()

        data.add("/storage/emulated/0/HOTC/pair/photos/")

        //setting adapter to recycler
//        recyclerView.adapter = EventsAdapter(::itemClicked)
//        recyclerView.layoutManager = GridLayoutManager(this, 4)
//        loadEventsData()
//        Log.d("TAG", "events data images............" + loadEventsData())
    }
    private fun itemClicked(photoModel: ModelFile.EventsPhotos) {
        startActivity(Intent(this,PhotosDisplay::class.java))
        Constants.photosFolderSelected = photoModel
    }

//    private fun loadEventsData() {
//        try{
//            val fileName = path.let { getFilesFromPath(it) }!!
//            val file = fileName[0]
//            val mainFile = file?.listFiles()?.filter {
//                it.name.trim() == "Background"
//            }?.mapNotNull {
//                it.listFiles().filter {
//                    it.name.trim() == "Photos"
//                }.mapNotNull {
//                    (recyclerView.adapter as EventsAdapter).items =
//                        getPhotoModelsFromFiles(it.listFiles()!!.toList().sortedBy { it.name }) as ArrayList<ModelFile.EventsPhotos>
//                }
//            }
//            }catch (e:Exception){
//            Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()
//        }
//    }

    fun getFilesFromPath(path: String, showHiddenFiles: Boolean = true, onlyFolders: Boolean = true): List<File> {
        val file = File(path)
        return file.listFiles()
            .filter { onlyFolders }
            .toList()
    }

    fun getPhotoModelsFromFiles(files: List<File>): List<ModelFile.EventsPhotos> {
        return files.map {
            val myBitmap = it.listFiles()?.get(0)?.absolutePath!!

            ModelFile.EventsPhotos(it.name,myBitmap)
        }
    }

    override fun onFileSelected(file: File) {
        val intent = Intent(this, PhotosDisplay::class.java)
        intent.putExtra("File",file)
        startActivity(intent)
    }
}