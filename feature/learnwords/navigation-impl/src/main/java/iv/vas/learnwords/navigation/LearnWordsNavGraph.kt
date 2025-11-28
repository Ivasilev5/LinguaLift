package iv.vas.learnwords.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import iv.vas.learnwords.navigation.LearnWordsRoutes.ChooseLevel
import iv.vas.learnwords.navigation.LearnWordsRoutes.LearnWords
import iv.vas.learnwords.navigation.LearnWordsRoutes.LevelProgress
import iv.vas.learnwords.navigation.LearnWordsRoutes.Onboarding
import iv.vas.learnwords.navigation.LearnWordsRoutes.Splash
import iv.vas.learnwords.ui.chooselevel.ChooseLevelScreen
import iv.vas.learnwords.ui.LearnWordsScreen
import iv.vas.learnwords.ui.LevelProgressScreen
import iv.vas.learnwords.ui.levelprogress.LevelProgressViewModel
import iv.vas.learnwords.ui.onboarding.OnBoardingScreen
import iv.vas.learnwords.ui.onboarding.SplashScreen

/**
 * Navigation graph for the LearnWords feature.
 * This composable sets up the NavHost with all screens and handles navigation.
 */
@Composable
fun LearnWordsNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigationImpl: LearnWordsNavigationImpl = LearnWordsNavigationImpl(),
    startDestination: LearnWordsRoutes = Splash,

) {
    // Set the nav controller in the navigation implementation
    navigationImpl.setNavController(navController)

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Splash Screen
        composable<Splash> {
            SplashScreen(
                onSplashFinished = {
                    navigationImpl.navigateToOnboarding()
                }
            )
        }

        // Onboarding Screen
        composable<Onboarding> {
            OnBoardingScreen(
                onOnboardingFinished = {
                    navigationImpl.navigateToChooseLevel()
                }
            )
        }

        // Choose Level Screen
        composable<ChooseLevel> { backStackEntry ->
            val route = backStackEntry.toRoute<ChooseLevel>()
            ChooseLevelScreen(
                onContinueClicked = { selectedLevelCode ->
                    // Navigate to LevelProgress with the selected level
                    navigationImpl.navigateToLevelProgress(
                        levelName = selectedLevelCode,
                        remainingWords = 20,
                        progressPercentage = 80
                    )
                }
            )
        }

        // Level Progress Screen
        composable<LevelProgress> { backStackEntry ->
            val route = backStackEntry.toRoute<LevelProgress>()
            LevelProgressScreen(
                levelName = route.levelName,
                onCloseClicked = {
                    navigationImpl.navigateBack()
                },
                onResetProgressClicked = {
                    // Could show a confirmation dialog here
                    // For now, just navigate back to choose level
                    navigationImpl.navigateToChooseLevel()
                },
                onLearnWordClicked = {word ->
                    navigationImpl.navigateToLearnWords(
                        levelName = route.levelName,
                        word = word,
                        phonetic = "" // Will be loaded from API
                    )
                }
            )
        }

        // Learn Words Screen
        composable<LearnWords> { backStackEntry ->
            val route = backStackEntry.toRoute<LearnWords>()
            LearnWordsScreen(
                level = route.levelName,
                word = route.word,
                onWordCompleted = {
                    // Navigate back to level progress
                    navigationImpl.navigateBack()
                }
            )
        }
    }
}
