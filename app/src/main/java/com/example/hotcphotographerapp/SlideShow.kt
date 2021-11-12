package com.example.hotcphotographerapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2
import com.example.hotcphotographerapp.Constants.Constants
import com.example.hotcphotographerapp.adapter.SlideShowAdapter
import com.example.hotcphotographerapp.databinding.ActivitySlideShowBinding
import com.example.hotcphotographerapp.utility.getImagesFromFolder
import com.example.hotcphotographerapp.utility.handleError
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.io.File

class SlideShow : AppCompatActivity() {
    private lateinit var binding: ActivitySlideShowBinding
    private val slideShowAdapter = SlideShowAdapter()
    private val compositeDisposable = CompositeDisposable()
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sliderHandler: Handler
    private lateinit var sliderRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySlideShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences(Constants.File.name, Context.MODE_PRIVATE)
        val title = sharedPreferences.getString("name",null)
        binding.layoutText.text = title

        initClicks()
        setupUi()
    }

    private fun initClicks(){
        binding.imageBack.setOnClickListener {
            onBackPressed()
            finish()
        }

        binding.imageHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun setupUi() {

        binding.viewPagerSlideShow.adapter = slideShowAdapter
        refreshContent(intent.getSerializableExtra("FileSlide") as File)

        sliderHandler = Handler(Looper.getMainLooper())
        sliderRunnable = Runnable {
            if (binding.viewPagerSlideShow.currentItem == slideShowAdapter.itemCount) {
                binding.viewPagerSlideShow.currentItem = 0
            } else {
                binding.viewPagerSlideShow.currentItem = binding.viewPagerSlideShow.currentItem + 1
            }
        }

        binding.viewPagerSlideShow.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            private var currentPage: Int = 0
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)

            }
        })
    }


    private fun refreshContent(folder: File) {
        binding.layoutText.text = folder.name
        compositeDisposable.add(
            Single.just(getImagesFromFolder(this, folder.absolutePath))
                .subscribe(

                    { files -> slideShowAdapter.setItems(files) },
                    { throwable: Throwable? -> handleError(throwable) }
                )
        )
    }
}