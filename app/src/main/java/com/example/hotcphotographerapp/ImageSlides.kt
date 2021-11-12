package com.example.hotcphotographerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.hotcphotographerapp.fragments.PlaceHolderForPhotos

class ImageSlides : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slides)

        var back = findViewById<ImageView>(R.id.imageViewBackSlide)
        var homeScreen = findViewById<ImageView>(R.id.imageViewHomeSlide)

        back.setOnClickListener {
            this?.onBackPressed()
            finish()
        }
        homeScreen.setOnClickListener{
            intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        var viewPager1 = findViewById<ViewPager>(R.id.viewPager)
        val position = intent.getIntExtra("position", 0)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        viewPager1.post {
            viewPager1.adapter = mSectionsPagerAdapter
            viewPager1.currentItem = position
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        return super.onOptionsItemSelected(item)
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceHolderForPhotos.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 5 total pages.(we will use 5 pages so change it to 5)
            return 8
        }
    }
}