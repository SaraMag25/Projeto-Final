package com.example.controleestudos.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "studies")
data class Study(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subject: String,
    val topic: String,
    val description: String
)