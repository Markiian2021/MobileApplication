package com.example.datastoresenkiv.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.datastoresenkiv.data.dao.SubjectDao
import com.example.datastoresenkiv.data.dao.SubjectLabsDao
import com.example.datastoresenkiv.data.entity.SubjectEntity
import com.example.datastoresenkiv.data.entity.SubjectLabEntity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@Database(entities = [SubjectEntity::class, SubjectLabEntity::class], version = 1)
abstract class IntoDataBase : RoomDatabase() {
    abstract val subjectsDao: SubjectDao
    abstract val subjectLabsDao: SubjectLabsDao
}

object DatabaseStorage {
    private val coroutineScope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        },
    )

    private var _database: IntoDataBase? = null

    fun getDatabase(context: Context): IntoDataBase {
        if (_database != null) return _database as IntoDataBase
        else {
            _database = Room.databaseBuilder(
                context,
                IntoDataBase::class.java, "IntoDataBase"
            ).build()

            preloadData()

            return _database as IntoDataBase
        }
    }

    private fun preloadData() {
        val listOfSubject = listOf(
            SubjectEntity(id = 1, title = "Електроживлення пристроїв та систем"),
            SubjectEntity(id = 2, title = "Мобільні інфокомунікаційні платформи"),
            SubjectEntity(id = 3, title = "Програмні платформа інфокомунікації"),
            SubjectEntity(id = 4, title = "Об'єктно-орієнтоване програмування C++"),
            SubjectEntity(id = 5, title = "Основи проєктування програмного забезпечення"),
            SubjectEntity(id = 6, title = "Технології представлення даних та СУБД"),
        )
        listOfSubject.forEach { subject ->
            coroutineScope.launch {
                _database?.subjectsDao?.addSubject(subject)
            }
        }
    }
}

