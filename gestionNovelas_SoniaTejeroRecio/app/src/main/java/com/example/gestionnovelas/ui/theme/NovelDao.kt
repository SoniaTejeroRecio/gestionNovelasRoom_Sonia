/*package com.example.gestionnovelas.ui.theme
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NovelDao {
    @Insert
    suspend fun insert(novel: Novel)

    @Query("SELECT * FROM novels")
    suspend fun getAllNovels(): List<Novel>
}


 */