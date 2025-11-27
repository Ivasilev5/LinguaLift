package iv.vas.learnwords.navigation

import kotlinx.serialization.Serializable

/**
 * Navigation routes for the LearnWords feature.
 * Uses kotlinx.serialization for type-safe navigation with data passing.
 */
sealed class LearnWordsRoutes {

    @Serializable
    data object Splash : LearnWordsRoutes()

    @Serializable
    data object Onboarding : LearnWordsRoutes()

    @Serializable
    data class ChooseLevel(
        val selectedLevels: List<String> = emptyList()
    ) : LearnWordsRoutes()

    @Serializable
    data class LevelProgress(
        val levelName: String,
        val remainingWords: Int = 20,
        val progressPercentage: Int = 80
    ) : LearnWordsRoutes()

    @Serializable
    data class LearnWords(
        val levelName: String,
        val word: String,
        val phonetic: String
    ) : LearnWordsRoutes()
}
