package iv.vas.learnwords.domain.usecase.learnwords

import iv.vas.learnwords.domain.model.Word
import iv.vas.learnwords.domain.repository.LearnRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWordsByLevelUseCase @Inject constructor(
    private val learnRepository: LearnRepository
) {
    suspend operator fun invoke(level: String): Flow<List<Word>> {
        return learnRepository.getWordsByLevel(level.uppercase())
    }
}
