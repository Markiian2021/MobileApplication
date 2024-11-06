package com.example.spacexsenkiv.data.entity.response.launchesResponse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "launchResponse")
data class LaunchResponse(
    @PrimaryKey val id: String,
    val links: LaunchLinks,
    val name: String,
    val date_local: String,
    val success: Boolean?,
    val upcoming: Boolean,
    val rocket: String,
    val details: String?,
    val cores: List<LaunchCores>
)

