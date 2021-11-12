package com.example.hotcphotographerapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedDispatcher
import com.example.hotcphotographerapp.ImageSlides
import com.example.hotcphotographerapp.R

/**
 * A simple [Fragment] subclass.
 * Use the [PlaceHolderForPhotos.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlaceHolderForPhotos : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_place_holder_for_photos, container, false)

        val imageView = rootView.findViewById<ImageView>(R.id.vpImg1)
//        val back = rootView.findViewById<ImageView>(R.id.imageViewBack)

//        val callback = rootView.findViewById<ImageView>(R.id.imageViewBack)
//
//        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
//            // Handle the back button event
//        }

//        back.setOnClickListener {
//            activity?.onBackPressed()
//        }

        when(arguments?.getInt(ARG_SECTION_NUMBER)){
            0->{
                imageView.setImageResource(R.drawable.event_pic1)
            }
            1->{
                imageView.setImageResource(R.drawable.event_pic12)
            }
            2->{

                imageView.setImageResource(R.drawable.event_pic13)
            }
            3->{

                imageView.setImageResource(R.drawable.event_pic14)
            }
            4->{

                imageView.setImageResource(R.drawable.event_pic2)
            }
            5->{

                imageView.setImageResource(R.drawable.event_pic4)
            }
            6->{

                imageView.setImageResource(R.drawable.event_pic5)
            }
            7->{

                imageView.setImageResource(R.drawable.event_pic2)
            }
            8->{

                imageView.setImageResource(R.drawable.event_pic2)
            }
            9->{
                imageView.setImageResource(R.drawable.event_pic1)
            }
            10->{
                imageView.setImageResource(R.drawable.event_pic12)
            }
            11->{

                imageView.setImageResource(R.drawable.event_pic13)
            }
            12->{

                imageView.setImageResource(R.drawable.event_pic14)
            }
            13->{

                imageView.setImageResource(R.drawable.event_pic2)
            }
            14->{

                imageView.setImageResource(R.drawable.event_pic4)
            }
            15->{

                imageView.setImageResource(R.drawable.event_pic5)
            }
            16->{

                imageView.setImageResource(R.drawable.event_pic2)
            }
            17->{

                imageView.setImageResource(R.drawable.event_pic2)
            }
        }
        return rootView
    }

    companion object {

        private val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): PlaceHolderForPhotos {
            val fragment = PlaceHolderForPhotos()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}

private fun OnBackPressedDispatcher.addCallback(placeHolderForPhotos: PlaceHolderForPhotos, function: () -> Unit) {

}
