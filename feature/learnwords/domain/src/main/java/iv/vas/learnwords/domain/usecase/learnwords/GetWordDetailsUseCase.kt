package iv.vas.learnwords.domain.usecase.learnwords

import iv.vas.learnwords.domain.model.WordDetails
import iv.vas.learnwords.domain.repository.LearnRepository
import javax.inject.Inject

class GetWordDetailsUseCase @Inject constructor(
    private val learnRepository: LearnRepository
) {
    suspend operator fun invoke(word: String): WordDetails {
        return learnRepository.getWordDetails(word)
    }
}
