package com.example.spacexsenkiv.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spacexsenkiv.data.entity.response.launchesResponse.LaunchResponse


@Dao
interface LaunchResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunch(launchResponse: LaunchResponse)

    @Query("SELECT * FROM launchResponse WHERE name LIKE :query OR id LIKE :query")
    suspend fun searchLaunch(query: String): List<LaunchResponse>

    @Query("DELETE FROM launchResponse WHERE id = :id")
    suspend fun deleteLaunchById(id: String)
}