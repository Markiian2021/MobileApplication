package com.example.datastoreviewmodelsenkiv.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "subjectsLabs"
)
data class SubjectLabEntity(
    @PrimaryKey (autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "subject_id")
    val subjectId: Int,
    val labName: Int,
    val title: String? = null,
    val description: String? = null,
    val comment: String? = null,
    val inProgress: Boolean = false,
    val isCompleted: Boolean = false,
)

