package com.example.museumsenkiv.data.entity.response.departmentsResponse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "departments")
data class Department(
    @PrimaryKey val departmentId: String,
    val displayName: String
)
