package iv.vas.learnwords.data.repository

import android.util.Log
import iv.vas.learnwords.data.database.LearnDao
import iv.vas.learnwords.data.database.model.CefrLevel
import iv.vas.learnwords.data.mapper.toDomain
import iv.vas.learnwords.data.mapper.toWordDetails
import iv.vas.learnwords.data.network.model.LearnApiService
import iv.vas.learnwords.domain.model.Word
import iv.vas.learnwords.domain.model.WordDetails
import iv.vas.learnwords.domain.repository.LearnRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LearnRepositoryImpl @Inject constructor(
    private val learnApiService: LearnApiService,
    private val learnDao: LearnDao
) : LearnRepository {

    override suspend fun initWordsIfNeeded() {
        val wordCount = learnDao.getWordsCount()
        if (wordCount == 0) {
            // Initialize words for each level
            val allWords = mutableListOf<iv.vas.learnwords.data.database.model.CefrWordEntity>()

            // Add A1 words
            allWords.addAll(
                WordDataProvider.getWordsForLevel(CefrLevel.A1)
                    .mapIndexed { index, word ->
                        iv.vas.learnwords.data.database.model.CefrWordEntity(
                            word = word,
                            level = CefrLevel.A1,
                            id = index + 1
                        )
                    }
            )

            // Add A2 words
            val a1Count = allWords.size
            allWords.addAll(
                WordDataProvider.getWordsForLevel(CefrLevel.A2)
                    .mapIndexed { index, word ->
                        iv.vas.learnwords.data.database.model.CefrWordEntity(
                            word = word,
                            level = CefrLevel.A2,
                            id = (a1Count + index + 1)
                        )
                    }
            )

            // Add B1 words
            val a2Count = allWords.size
            allWords.addAll(
                WordDataProvider.getWordsForLevel(CefrLevel.B1)
                    .mapIndexed { index, word ->
                        iv.vas.learnwords.data.database.model.CefrWordEntity(
                            word = word,
                            level = CefrLevel.B1,
                            id = (a2Count + index + 1)
                        )
                    }
            )

            // Add B2 words
            val b1Count = allWords.size
            allWords.addAll(
                WordDataProvider.getWordsForLevel(CefrLevel.B2)
                    .mapIndexed { index, word ->
                        iv.vas.learnwords.data.database.model.CefrWordEntity(
                            word = word,
                            level = CefrLevel.B2,
                            id = (b1Count + index + 1)
                        )
                    }
            )

            // Add C1 words
            val b2Count = allWords.size
            allWords.addAll(
                WordDataProvider.getWordsForLevel(CefrLevel.C1)
                    .mapIndexed { index, word ->
                        iv.vas.learnwords.data.database.model.CefrWordEntity(
                            word = word,
                            level = CefrLevel.C1,
                            id = (b2Count + index + 1)
                        )
                    }
            )

            learnDao.insertWords(allWords)
        }
    }

    override suspend fun getWordsByLevel(level: String): Flow<List<Word>> {
        // Ensure words are initialized before getting them
        initWordsIfNeeded()

        // Try to find by exact level name first, then by enum value
        return try {
            val cefrLevel = CefrLevel.valueOf(level.uppercase())
            learnDao.getWordsByLevel(cefrLevel.name).map { it.toDomain() }
        } catch (e: IllegalArgumentException) {
            // If level is not a valid enum, return empty flow
            flow { emit(emptyList()) }
        }
    }

    override suspend fun getNextWordToLearn(level: String): Word? {
        // Try to find by exact level name first, then by enum value
        return try {
            val cefrLevel = CefrLevel.valueOf(level.uppercase())
            learnDao.getNextWordToLearn(cefrLevel.name)?.toDomain()
        } catch (e: IllegalArgumentException) {
            // If level is not a valid enum, try direct search
            null
        }
    }

    override suspend fun getWordDetails(word: String): WordDetails {
        return try {
            val response = learnApiService.getWord(word)
            if (response.isSuccessful) {
                response.body()?.firstOrNull()?.toWordDetails() ?: throw Exception("Empty response")
            } else {
                throw Exception("API error: ${response.code()}")
            }
        } catch (e: Exception) {
            // Return cached data if API fails
            Log.d("getWordDetails", e.message.toString())
            val cachedWord = learnDao.getWordByName(word)?.toDomain()
            if (cachedWord != null) {
                WordDetails(
                    word = cachedWord.word,
                    phonetic = cachedWord.phonetic,
                    phonetics = emptyList(),
                    meanings = emptyList()
                )
            } else {
                throw e
            }
        }
    }

    override suspend fun updateWordProgress(id: Int, progress: Int) {
        learnDao.updateWordProgress(id, progress)
    }

    override suspend fun markWordLearned(id: Int) {
        learnDao.markWordLearned(id)
    }
}