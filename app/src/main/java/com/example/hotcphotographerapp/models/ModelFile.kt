package com.example.hotcphotographerapp.models

class ModelFile {
    data class PartnerPhotos(
        val partnerImage : Int
    )
    data class EventsPhotos(
        val eventImage: String,
        val eventName: String?
    )
    data class PartnerVideos(
        val partnerImage : Int
    )
}