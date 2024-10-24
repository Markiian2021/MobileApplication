package com.example.datastoreviewmodelsenkiv.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datastoreviewmodelsenkiv.data.dao.SubjectDao
import com.example.datastoreviewmodelsenkiv.data.dao.SubjectLabsDao
import com.example.datastoreviewmodelsenkiv.data.entity.SubjectEntity
import com.example.datastoreviewmodelsenkiv.data.entity.SubjectLabEntity

@Database(entities = [SubjectEntity::class, SubjectLabEntity::class], version = 1)
abstract class IntoDataBase : RoomDatabase() {
    abstract val subjectsDao: SubjectDao
    abstract val subjectLabsDao: SubjectLabsDao
}

