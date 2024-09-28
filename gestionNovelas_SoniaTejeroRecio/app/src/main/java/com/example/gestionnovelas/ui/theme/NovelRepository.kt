// app/src/main/java/com/example/gestionnovelas/ui/theme/NovelRepository.kt
/*package com.example.gestionnovelas.ui.theme

import android.content.Context
import com.example.gestionnovelas.ui.theme.DatabaseModule
import com.example.gestionnovelas.ui.theme.Novel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NovelRepository(private val context: Context) {

    fun addNovel(novel: Novel) {
        val db = DatabaseModule.provideDatabase(context)
        val novelDao = db.novelDao()

        CoroutineScope(Dispatchers.IO).launch {
            novelDao.insert(novel)
        }
    }
}

 */