package com.example.datastoresenkiv.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.datastoresenkiv.data.entity.SubjectLabEntity

@Dao
interface SubjectLabsDao {

    @Query("SELECT * FROM subjectsLabs")
    suspend fun getAllSubjectLabs(): List<SubjectLabEntity>

    @Query("SELECT * FROM subjectsLabs WHERE subject_id = :subjectId ORDER BY labName ASC")
    suspend fun getSubjectLabsBySubjectId(subjectId: Int): List<SubjectLabEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subjectLab: SubjectLabEntity)

    @Delete
    suspend fun delete(subjectLab: SubjectLabEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSubjectLab(subjectLabEntity: SubjectLabEntity)

    @Update
    suspend fun update(subjectLab: SubjectLabEntity)
}
