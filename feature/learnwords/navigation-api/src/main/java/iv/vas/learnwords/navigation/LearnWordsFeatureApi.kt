package iv.vas.learnwords.navigation

/**
 * Navigation API for the LearnWords feature.
 * This interface defines the contract for navigation within the feature,
 * allowing screens to request navigation without knowing implementation details.
 */
interface LearnWordsFeatureApi {

    /**
     * Navigate to the splash screen
     */
    fun navigateToSplash()

    /**
     * Navigate to the onboarding screen
     */
    fun navigateToOnboarding()

    /**
     * Navigate to level selection screen with optional pre-selected levels
     */
    fun navigateToChooseLevel(selectedLevels: List<String> = emptyList())

    /**
     * Navigate to level progress screen
     */
    fun navigateToLevelProgress(
        levelName: String,
        remainingWords: Int = 20,
        progressPercentage: Int = 80
    )

    /**
     * Navigate to the learn words screen for a specific word
     */
    fun navigateToLearnWords(
        levelName: String,
        word: String,
        phonetic: String
    )

    /**
     * Navigate back to the previous screen
     */
    fun navigateBack()

    /**
     * Navigate back to the root screen
     */
    fun navigateBackToRoot()
}
