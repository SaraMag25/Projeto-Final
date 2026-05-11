package com.example.controleestudos.data
import android.content.Context
import androidx.room.*

@Database(entities = [Study::class], version = 1)
abstract class StudyDatabase : RoomDatabase() {
    abstract fun studyDao(): StudyDao
    companion object {
        @Volatile private var INSTANCE: StudyDatabase? = null
        fun getDatabase(context: Context): StudyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    StudyDatabase::class.java, "study_db").build()
                INSTANCE = instance
                instance
            }
        }
    }
}