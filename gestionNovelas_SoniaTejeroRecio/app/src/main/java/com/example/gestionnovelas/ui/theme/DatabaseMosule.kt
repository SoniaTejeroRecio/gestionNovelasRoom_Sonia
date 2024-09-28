/*package com.example.gestionnovelas.ui.theme
import android.content.Context
import androidx.room.Room

object DatabaseModule {
    private var database: AppDatabase? = null

    fun provideDatabase(context: Context): AppDatabase {
        return database ?: Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "novel_database"
        ).build().also { database = it }
    }
}

 */