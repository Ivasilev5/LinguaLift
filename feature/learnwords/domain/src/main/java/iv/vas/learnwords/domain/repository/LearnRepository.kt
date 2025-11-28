package iv.vas.learnwords.domain.repository

import iv.vas.learnwords.domain.model.Word
import iv.vas.learnwords.domain.model.WordDetails
import kotlinx.coroutines.flow.Flow

interface LearnRepository {

    suspend fun initWordsIfNeeded()

    suspend fun getWordsByLevel(level: String): Flow<List<Word>>

    suspend fun getNextWordToLearn(level: String): Word?

    suspend fun getWordDetails(word: String): WordDetails

    suspend fun updateWordProgress(id: Int, progress: Int)

    suspend fun markWordLearned(id: Int)
}