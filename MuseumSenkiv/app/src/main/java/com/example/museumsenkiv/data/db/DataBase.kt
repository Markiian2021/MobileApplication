package com.example.museumsenkiv.data.db

import android.content.Context
import androidx.room.Room

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

