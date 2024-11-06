package com.example.spacexsenkiv.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.spacexsenkiv.data.dao.LaunchResponseDao
import com.example.spacexsenkiv.data.entity.Converters
import com.example.spacexsenkiv.data.entity.response.launchesResponse.LaunchResponse

@Database(entities = [LaunchResponse::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class IntoDataBase : RoomDatabase() {
    abstract fun launchResponseDao(): LaunchResponseDao
}

