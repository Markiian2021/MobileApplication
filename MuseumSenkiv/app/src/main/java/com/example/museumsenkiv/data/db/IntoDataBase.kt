package com.example.museumsenkiv.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.museumsenkiv.data.dao.MuseumResponseDao
import com.example.museumsenkiv.data.entity.response.artobjectResponse.ArtObject
import com.example.museumsenkiv.data.entity.response.departmentsResponse.Department

@Database(entities = [ArtObject::class, Department::class], version = 3, exportSchema = false)
abstract class IntoDataBase : RoomDatabase() {
    abstract fun museumResponseDao(): MuseumResponseDao
}



