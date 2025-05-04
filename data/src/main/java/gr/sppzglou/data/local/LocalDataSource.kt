package gr.sppzglou.data.local

import gr.sppzglou.data.local.entities.WordEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val db: AppDatabase
) {
    fun getWord(word: String): Flow<WordEntity> = db.dao().get(word)

    suspend fun findWord(word: String): WordEntity? = db.dao().find(word)

    suspend fun insertWords(list: List<WordEntity>) = db.dao().insert(list)

    suspend fun updateFav(word: String, flag: Boolean): Unit = db.dao().updateFav(word, flag)

    fun getFavs(): Flow<List<WordEntity>> = db.dao().getFavs()
}
