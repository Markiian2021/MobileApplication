package com.example.spacexsenkiv.data.entity

import android.content.Context
import androidx.room.Room
import com.example.spacexsenkiv.data.db.IntoDataBase

object DataBase {
    private var _database: IntoDataBase? = null

    fun getDatabase(context: Context): IntoDataBase {
        return _database ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                IntoDataBase::class.java,
                "IntoDataBase"
            )
                .fallbackToDestructiveMigration()
                .build()
            _database = instance
            instance
        }
    }
}
