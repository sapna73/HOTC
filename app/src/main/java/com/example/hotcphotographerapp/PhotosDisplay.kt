package com.example.hotcphotographerapp

import android.content.ContentUris
import android.content.Intent
import android.database.ContentObserver
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hotcphotographerapp.adapter.PhotosAdapter
import com.example.hotcphotographerapp.models.ModelFile
import com.example.hotcphotographerapp.models.SharedStoragePhoto
import com.example.hotcphotographerapp.utility.getImagesFromFolder
import com.example.hotcphotographerapp.utility.handleError
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_photos_display.*
import kotlinx.android.synthetic.main.activity_slide_show.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class PhotosDisplay : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    lateinit var photosAdapter: PhotosAdapter
    private lateinit var file: File
    private val compositeDisposable = CompositeDisposable()

    private lateinit var contentObserver: ContentObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos_display)

        recyclerView  = findViewById(R.id.rvFeaturedPartners)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL)
        recyclerView.adapter = photosAdapter
        recyclerView.layoutManager = staggeredGridLayoutManager
        initContentObserver()

        file = intent.getSerializableExtra("File") as File

        var back = findViewById<ImageView>(R.id.imageViewBackPhotos)
        var homeScreen = findViewById<ImageView>(R.id.imageViewHome)
        var slideShowBtn = findViewById<ImageView>(R.id.quickSlideShow)

        back.setOnClickListener {
            intent = Intent( this, MainActivity::class.java)
            startActivity(intent)

        }
        homeScreen.setOnClickListener{
            intent = Intent( this, MainActivity::class.java)
            startActivity(intent)

        }

        slideShowBtn.setOnClickListener{
            intent = Intent( this, SlideShow::class.java)
            intent.putExtra("FileSlide",file)
            startActivity(intent)
        }

    }

    private fun loadPhotosFromExternalStorageIntoRecycler(){
        lifecycleScope.launch{
            val photos1 = loadPhotosFromExternalStorage()
            photosAdapter.submitList(photos1)
        }
    }

    private fun initContentObserver(){
        contentObserver = object : ContentObserver(null){
            override fun onChange(selfChange: Boolean) {
                loadPhotosFromExternalStorageIntoRecycler()
            }
        }
        contentResolver.registerContentObserver(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            true,
            contentObserver
        )
    }

    private suspend fun loadPhotosFromExternalStorage(): List<SharedStoragePhoto>{
        return withContext(Dispatchers.IO){
           val collection = sdk29AndUp {
               MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
           } ?: MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.WIDTH,
                MediaStore.Images.Media.HEIGHT
            )
            val photos = mutableListOf<SharedStoragePhoto>()
            contentResolver.query(
                collection as Uri,
                projection,
                null,
                null,
                "${MediaStore.Images.Media.DISPLAY_NAME} ASC"
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                val widthColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
                val heightColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)

                while(cursor.moveToNext()){
                    val id = cursor.getLong(idColumn)
                    val displayName = cursor.getString(displayNameColumn)
                    val width = cursor.getInt(widthColumn)
                    val height = cursor.getInt(heightColumn)
                    val contentUri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                    photos.add(SharedStoragePhoto(id, displayName, width, height, contentUri))
                }
                photos.toList()
            } ?: listOf()
        }

    }

//    private var photosAdapter = PhotosAdapter(
//        listOf(
//            ModelFile.PartnerPhotos(R.drawable.event_pic1),
//            ModelFile.PartnerPhotos(R.drawable.event_pic16),
//            ModelFile.PartnerPhotos(R.drawable.event_pic2),
//            ModelFile.PartnerPhotos(R.drawable.event_pic3),
//            ModelFile.PartnerPhotos(R.drawable.event_pic4),
//            ModelFile.PartnerPhotos(R.drawable.event_pic5),
//            ModelFile.PartnerPhotos(R.drawable.event_pic34),
//            ModelFile.PartnerPhotos(R.drawable.event_pic2),
//            ModelFile.PartnerPhotos(R.drawable.event_pic16),
//            ModelFile.PartnerPhotos(R.drawable.event_pic1),
//            ModelFile.PartnerPhotos(R.drawable.event_pic15),
//            ModelFile.PartnerPhotos(R.drawable.event_pic1),
//            ModelFile.PartnerPhotos(R.drawable.event_pic17),
//            ModelFile.PartnerPhotos(R.drawable.event_pic1),
//            ModelFile.PartnerPhotos(R.drawable.event_pic25),
//            ModelFile.PartnerPhotos(R.drawable.event_pic16),
//            ModelFile.PartnerPhotos(R.drawable.event_pic5),
//            ModelFile.PartnerPhotos(R.drawable.event_pic16),
//            ModelFile.PartnerPhotos(R.drawable.event_pic5),
//            ModelFile.PartnerPhotos(R.drawable.event_pic14),
//            ModelFile.PartnerPhotos(R.drawable.event_pic5),
//            ModelFile.PartnerPhotos(R.drawable.event_pic13),
//            ModelFile.PartnerPhotos(R.drawable.event_pic1),
//            ModelFile.PartnerPhotos(R.drawable.event_pic16),
//            ModelFile.PartnerPhotos(R.drawable.event_pic2),
//            ModelFile.PartnerPhotos(R.drawable.event_pic3),
//            ModelFile.PartnerPhotos(R.drawable.event_pic4),
//            ModelFile.PartnerPhotos(R.drawable.event_pic5),
//            ModelFile.PartnerPhotos(R.drawable.event_pic15)
//        )
//    )

    override fun onDestroy() {
        super.onDestroy()
        contentObserver.unregisterContentObserver(contentObserver)
    }

    private fun setupUi() {

        rvFeaturedPartners.adapter = photosAdapter
        refreshContent(intent.getSerializableExtra("File") as File)

    }

    private fun refreshContent(folder: File) {
        layoutText.text = folder.name
        compositeDisposable.add(
            Single.just(getImagesFromFolder(this, folder.absolutePath))
                .subscribe(
                    { files -> photosAdapter.setItems(files) },
                    { throwable: Throwable? -> handleError(throwable) }
                )
        )
    }
}

private fun ContentObserver.unregisterContentObserver(contentObserver: ContentObserver) {

}


