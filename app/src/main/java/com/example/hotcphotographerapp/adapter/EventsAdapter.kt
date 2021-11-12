package com.example.hotcphotographerapp.adapter

import android.content.Context
import android.os.Build
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotcphotographerapp.R
import com.example.hotcphotographerapp.models.ModelFile
import com.example.hotcphotographerapp.utility.Files
import java.io.File
import kotlin.reflect.KFunction1

class EventsAdapter(val context: Context, private val listener: Files ) : RecyclerView.Adapter<EventsAdapter.EventsVH>() {

    var items = ArrayList<ModelFile.EventsPhotos>()
    val events: (ModelFile.EventsPhotos) -> Unit
        get() {
            TODO()
        }

    fun setDataList(data : ArrayList<ModelFile.EventsPhotos>){
        this.items = data
    }
    val homeFolder = File("/storage/emulated/0/HOTC")
    val homeFile = homeFolder.listFiles()

    inner class EventsVH(view: View) : RecyclerView.ViewHolder(view){
        private val imageViewEvents = view.findViewById<ImageView>(R.id.imageViewEventsItem)
//        val photoFolder = File("/storage/emulated/0/HOTC/pairs/photos"  + "/event1")
//        @RequiresApi(Build.VERSION_CODES.R)
//        fun bind(data: ModelFile.EventsPhotos){
//            Environment.getStorageDirectory().toString() + photoFolder
//            val file = File(
//                Environment.getStorageDirectory(),
//                "/event1"
//            ).toString() + photoFolder
//            Glide.with(itemView)
//                .load(file)
//                .into(imageViewEvents)
//        }

        init {
            with(itemView){
                setOnClickListener{
                    events.invoke(items[adapterPosition])
                }
            }
        }
        fun bind(data:ModelFile.EventsPhotos){
            with(itemView){
                Glide.with(context).load(data.eventImage).into(imageViewEvents)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapter.EventsVH {

        return EventsVH(LayoutInflater.from(parent.context).inflate(R.layout.item_events, parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onBindViewHolder(holder: EventsAdapter.EventsVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


}