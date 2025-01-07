package com.example.museumsenkiv.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.museumsenkiv.data.entity.response.artobjectResponse.ArtObject
import com.example.museumsenkiv.data.entity.response.departmentsResponse.Department
import kotlinx.coroutines.flow.Flow

@Dao
interface MuseumResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDepartment(department: Department)

    @Query("SELECT * FROM departments")
    suspend fun getDepartments(): List<Department>

    @Query("SELECT * FROM art_objects")
    fun getAllArtObjects(): List<ArtObject>

    @Query("SELECT * FROM departments WHERE departmentId = :departmentId")
    suspend fun getDepartmentById(departmentId: String): Department?

    @Query("SELECT * FROM art_objects WHERE department = :departmentId")
    suspend fun getArtObjectsByDepartment(departmentId: Int): List<ArtObject>

    @Query("SELECT * FROM art_objects WHERE objectID = :objectID")
    suspend fun getArtObjectById(objectID: Int): ArtObject?
}




