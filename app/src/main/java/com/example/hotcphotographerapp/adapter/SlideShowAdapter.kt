package com.example.hotcphotographerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotcphotographerapp.R
import com.example.hotcphotographerapp.utility.loadImage
import java.io.File

class SlideShowAdapter () : RecyclerView.Adapter<SlideShowAdapter.SlideSHowVH>() {

    private var mFiles = mutableListOf<File>()

    fun setItems(files: List<File>) {
        mFiles = files.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mFiles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideSHowVH {
        return SlideSHowVH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_slideshow,parent,false))
    }
    override fun onBindViewHolder(holder: SlideSHowVH, position: Int) {
        holder.bind(mFiles[position])
    }

    class SlideSHowVH(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val imageView = itemView.findViewById<ImageView>(R.id.itemSlideShow)

        fun bind(file: File) {
            imageView.loadImage(file)
        }
    }
}