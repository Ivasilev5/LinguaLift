package iv.vas.learnwords.domain.usecase.learnwords

import iv.vas.learnwords.domain.repository.LearnRepository
import javax.inject.Inject

class UpdateWordProgressUseCase @Inject constructor(
    private val learnRepository: LearnRepository
) {
    suspend operator fun invoke(id: Int, progress: Int) {
        learnRepository.updateWordProgress(id, progress)
    }
}
