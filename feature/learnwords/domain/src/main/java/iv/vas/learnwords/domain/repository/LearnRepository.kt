package iv.vas.learnwords.domain.repository

import iv.vas.learnwords.domain.model.Word

interface LearnRepository {

    suspend fun addToLearnList(word: Word)

    suspend fun deleteFromLearnList(word: Word)

    suspend fun getWord() : Result<Word>

    suspend fun getListOfWord() : Result<List<Word>>

}