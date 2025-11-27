package iv.vas.learnwords.navigation

import androidx.navigation.NavController
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of LearnWordsFeatureApi that provides type-safe navigation.
 * This class uses Jetpack Navigation Compose with serialized routes for type safety.
 */
@Singleton
class LearnWordsNavigationImpl @Inject constructor() : LearnWordsFeatureApi {

    private var navController: NavController? = null

    /**
     * Set the NavController instance. This should be called when the navigation host is created.
     */
    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateToSplash() {
        navController?.navigate(LearnWordsRoutes.Splash) {
            popUpTo(navController?.graph?.startDestinationId ?: 0) {
                inclusive = true
            }
        }
    }

    override fun navigateToOnboarding() {
        navController?.navigate(LearnWordsRoutes.Onboarding) {
            popUpTo(LearnWordsRoutes.Splash) {
                inclusive = true
            }
        }
    }

    override fun navigateToChooseLevel(selectedLevels: List<String>) {
        navController?.navigate(LearnWordsRoutes.ChooseLevel(selectedLevels = selectedLevels))
    }

    override fun navigateToLevelProgress(
        levelName: String,
        remainingWords: Int,
        progressPercentage: Int
    ) {
        navController?.navigate(
            LearnWordsRoutes.LevelProgress(
                levelName = levelName,
                remainingWords = remainingWords,
                progressPercentage = progressPercentage
            )
        )
    }

    override fun navigateToLearnWords(
        levelName: String,
        word: String,
        phonetic: String
    ) {
        navController?.navigate(
            LearnWordsRoutes.LearnWords(
                levelName = levelName,
                word = word,
                phonetic = phonetic
            )
        )
    }

    override fun navigateBack() {
        navController?.popBackStack()
    }

    override fun navigateBackToRoot() {
        navController?.popBackStack(
            navController?.graph?.startDestinationId ?: 0,
            inclusive = false
        )
    }
}
