package gr.sppzglou.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gr.sppzglou.data.local.entities.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: List<WordEntity>)

    @Query("SELECT * FROM Words WHERE word = :word")
    fun get(word: String): Flow<WordEntity>

    @Query("SELECT * FROM Words WHERE word = :word")
    suspend fun find(word: String): WordEntity?

    @Query("UPDATE Words SET isFav = :flag WHERE word = :wordId")
    suspend fun updateFav(wordId: String, flag: Boolean)

    @Query("SELECT * FROM Words WHERE isFav = 1")
    fun getFavs(): Flow<List<WordEntity>>

}
