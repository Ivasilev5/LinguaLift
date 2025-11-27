package iv.vas.learnwords.data.repository

import iv.vas.learnwords.domain.model.Word
import iv.vas.learnwords.domain.repository.LearnRepository

class LearnRepositoryImpl(

) : LearnRepository {
    override suspend fun addToLearnList(word: Word) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFromLearnList(word: Word) {
        TODO("Not yet implemented")
    }

    override suspend fun getWord(): Result<Word> {
        TODO("Not yet implemented")
    }

    override suspend fun getListOfWord(): Result<List<Word>> {
        TODO("Not yet implemented")
    }
}