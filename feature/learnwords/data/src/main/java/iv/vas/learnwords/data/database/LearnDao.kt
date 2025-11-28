package iv.vas.learnwords.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import iv.vas.learnwords.data.database.model.CefrWordEntity
import iv.vas.learnwords.data.database.model.CefrLevel
import kotlinx.coroutines.flow.Flow

@Dao
interface LearnDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: CefrWordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<CefrWordEntity>)

    @Query("SELECT COUNT(*) FROM cefr_words")
    suspend fun getWordsCount(): Int

    @Query("SELECT * FROM cefr_words WHERE level = :level ORDER BY id ASC")
    fun getWordsByLevel(level: String): Flow<List<CefrWordEntity>>

    @Query("SELECT * FROM cefr_words WHERE level = :level AND isLearned = 0 ORDER BY id ASC LIMIT 1")
    suspend fun getNextWordToLearn(level: String): CefrWordEntity?

    @Query("SELECT * FROM cefr_words WHERE id = :id")
    suspend fun getWordById(id: Int): CefrWordEntity?

    @Update
    suspend fun updateWord(word: CefrWordEntity)

    @Query("UPDATE cefr_words SET progress = :progress WHERE id = :id")
    suspend fun updateWordProgress(id: Int, progress: Int)

    @Query("UPDATE cefr_words SET isLearned = 1 WHERE id = :id")
    suspend fun markWordLearned(id: Int)

    @Query("SELECT * FROM cefr_words WHERE word = :word LIMIT 1")
    suspend fun getWordByName(word: String): CefrWordEntity?
}