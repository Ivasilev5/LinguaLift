package iv.vas.learnwords.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import iv.vas.core_ui.theme.LinguaLiftTheme
import iv.vas.learnwords.ui.learnwords.LearnWordsViewModel
import kotlin.math.min

@Composable
fun LearnWordsScreen(
    modifier: Modifier = Modifier,
    word : String,
    level: String = "A1",
    viewModel: LearnWordsViewModel = hiltViewModel(),
    onWordCompleted: () -> Unit = {}
) {
    val currentWord by viewModel.currentWord.collectAsState()
    val definition by viewModel.definition.collectAsState()
    val wordAudio by viewModel.wordAudio.collectAsState()
    val example by viewModel.example.collectAsState()
    val phonetic by viewModel.phonetic.collectAsState()
    val partOfSpeech by viewModel.partOfSpeech.collectAsState()
    val progress by viewModel.progress.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // Card flip state
    val isCardFlipped = remember { mutableStateOf(false) }
    val rotation = animateFloatAsState(
        targetValue = if (isCardFlipped.value) 180f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "cardRotation"
    )

    // Load the specific word details from API
    LaunchedEffect(word, level) {
        viewModel.loadWordByName(word, level)
    }

    LaunchedEffect(isCardFlipped.value) {
        if (isCardFlipped.value){
            viewModel.playWordAudio(wordAudio ?: "")
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header with level and progress
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$level Level",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Progress indicator
            LinearProgressIndicator(
                progress = { progress / 100f },
                modifier = Modifier.fillMaxWidth(0.8f),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            Text(
                text = "$progress% complete",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Word Card - Centered
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .graphicsLayer {
                    rotationY = rotation.value
                    cameraDistance = 12f * density
                }
                .clickable {
                    isCardFlipped.value = !isCardFlipped.value
                }
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(24.dp)
                ),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    error != null -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Error loading word",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = error ?: "",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    currentWord == null -> {
                        Text(
                            text = "No more words to learn in this level!",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                    else -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    rotationY = if (isCardFlipped.value) 180f else 0f
                                }
                        ) {
                            if (!isCardFlipped.value) {
                                // Front of card - show only the word
                                Text(
                                    text = currentWord?.word ?: word,
                                    style = MaterialTheme.typography.displayLarge.copy(
                                        fontSize = 48.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                // Hint text
                                Text(
                                    text = "Tap to reveal details",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textAlign = TextAlign.Center
                                )
                            } else {
                                // Back of card - show word with details
                                // Word
                                Text(
                                    text = currentWord?.word ?: word,
                                    style = MaterialTheme.typography.displayLarge.copy(
                                        fontSize = 48.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                // Part of speech
                                if (partOfSpeech != null) {
                                    Text(
                                        text = partOfSpeech ?: "",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium
                                        ),
                                        color = MaterialTheme.colorScheme.secondary,
                                        textAlign = TextAlign.Center
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                // Phonetic transcription
                                if (phonetic != null) {
                                    Text(
                                        text = phonetic ?: "",
                                        style = MaterialTheme.typography.headlineMedium.copy(
                                            fontSize = 18.sp
                                        ),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        textAlign = TextAlign.Center
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                // Example
                                if (example != null) {
                                    Text(
                                        text = "Example:",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "\"${example ?: ""}\"",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        textAlign = TextAlign.Center,
                                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                // Hint text
                                Text(
                                    text = "Tap to flip back",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }

        // Action Buttons
        if (currentWord != null && !isLoading && error == null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        // Mark as learned if word exists in database
                        currentWord?.let { word ->
                            if (word.id > 0) {
                                viewModel.markLearned()
                            }
                        }
                        onWordCompleted()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "I Know",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                }

                Button(
                    onClick = {
                        // Update progress if word exists in database
                        currentWord?.let { word ->
                            if (word.id > 0) {
                                val newProgress = minOf(progress + 25, 95)
                                viewModel.updateProgress(newProgress)
                            }
                        }
                        onWordCompleted()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Need Practice",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Bottom spacing
        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Previews are disabled for now since they require ViewModel
// @Preview(showBackground = true)
// @Composable
// private fun LearnWordsScreenLightPreview() {
//     LinguaLiftTheme(darkTheme = false) {
//         LearnWordsScreen()
//     }
// }
//
// @Preview(showBackground = true)
// @Composable
// private fun LearnWordsScreenDarkPreview() {
//     LinguaLiftTheme(darkTheme = true) {
//         LearnWordsScreen()
//     }
// }
// }