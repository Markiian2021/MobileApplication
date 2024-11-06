package com.example.spacexsenkiv.data.entity.response.launchesResponse

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "launch_cores")
data class LaunchCores(
    @PrimaryKey val coreId: String,
    val gridfins: Boolean?,
    val legs: Boolean?,
    val reused: Boolean?,
    val landing_attempt: Boolean?,
    val landing_success: Boolean?,
)
