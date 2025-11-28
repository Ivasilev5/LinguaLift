package iv.vas.learnwords.domain.usecase.learnwords

import iv.vas.learnwords.domain.model.Word
import iv.vas.learnwords.domain.repository.LearnRepository
import javax.inject.Inject

class LoadNextWordUseCase @Inject constructor(
    private val learnRepository: LearnRepository
) {
    suspend operator fun invoke(level: String): Word? {
        return learnRepository.getNextWordToLearn(level)
    }
}
