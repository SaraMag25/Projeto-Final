package com.example.controleestudos.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyDao {
    @Query("SELECT * FROM studies ORDER BY id DESC")
    fun getAllStudies(): Flow<List<Study>>

    @Insert
    suspend fun insert(study: Study)
}