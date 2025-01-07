package com.example.museumsenkiv.data.entity.response.artobjectResponse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "art_objects"
)
data class ArtObject(
    @PrimaryKey(autoGenerate = true)
    val objectID: Int = 0,
    val artistDisplayBio: String,
    val artists:String,
    val title: String,
    val department: String,
    val primaryImage: String,
    val country: String?,
    val artistDisplayName: String,
    val artistNationality: String,
    val objectDate: String,
    val medium: String,
    val accessionYear: String?,
    val objectURL: String,
)
