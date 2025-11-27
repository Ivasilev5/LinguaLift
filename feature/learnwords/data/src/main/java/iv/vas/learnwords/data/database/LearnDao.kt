package iv.vas.learnwords.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iv.vas.learnwords.data.database.model.CefrWordEntity
import iv.vas.learnwords.domain.model.Word


@Dao
interface LearnDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordToDb(word: Word)

    @Delete
    suspend fun deleteWordFromDb(word: Word)

    @Query("SELECT * FROM cefr_words WHERE level = :level")
    suspend fun getWordsForLevel(level : String) : List<CefrWordEntity>


}