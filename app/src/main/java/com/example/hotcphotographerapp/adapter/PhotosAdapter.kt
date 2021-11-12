package com.example.hotcphotographerapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.hotcphotographerapp.ImageSlides
import com.example.hotcphotographerapp.R
import com.example.hotcphotographerapp.models.ModelFile
import com.example.hotcphotographerapp.models.SharedStoragePhoto
import java.io.File

class PhotosAdapter(private val partners : List<ModelFile.PartnerPhotos>) :
    RecyclerView.Adapter<PhotosAdapter.PartnerVH>() {

    private var mFiles = mutableListOf<File>()

    fun setItems(files: List<File>) {
        mFiles = files.toMutableList()
        notifyDataSetChanged()
    }

        inner class PartnerVH(view: View) : RecyclerView.ViewHolder(view) {

            private val imageView = view.findViewById<ImageView>(R.id.image_items)

            fun bind(partners: ModelFile.PartnerPhotos){
                imageView.setImageResource(partners.partnerImage)
            }

            init {
                imageView.setOnClickListener{
                    val intent = Intent(itemView.context, ImageSlides::class.java)
                    intent.putExtra("position",adapterPosition)
                    itemView.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnerVH {

            return PartnerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_photos,parent,false))
        }

        override fun onBindViewHolder(holder: PartnerVH, position: Int) {
            holder.bind(partners[position])
        }

        override fun getItemCount(): Int {
            return partners.size
        }

    fun submitList(photos1: List<SharedStoragePhoto>) {

    }
}