package iv.vas.learnwords.ui.levelprogress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iv.vas.learnwords.domain.model.Word
import iv.vas.learnwords.domain.usecase.learnwords.GetWordsByLevelUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelProgressViewModel @Inject constructor(
    private val getWordsByLevelUseCase: GetWordsByLevelUseCase
) : ViewModel() {

    private val _words = MutableStateFlow<List<Word>>(emptyList())
    val words: StateFlow<List<Word>> = _words.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private var currentLevel: String = ""

    fun loadWordsForLevel(level: String) {
        if (currentLevel == level && _words.value.isNotEmpty()) {
            return
        }

        currentLevel = level
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val wordsFlow = getWordsByLevelUseCase(level)
                wordsFlow.collectLatest { words ->
                    // If no words from database, use fallback
                    if (words.isEmpty()) {
                        _words.value = listOf(
                            Word(id = 1, word = "hello", level = level, progress = 0, isLearned = false),
                            Word(id = 2, word = "world", level = level, progress = 50, isLearned = false),
                            Word(id = 3, word = "kotlin", level = level, progress = 100, isLearned = true)
                        )
                    } else {
                        _words.value = words
                    }
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                // Fallback to hardcoded words on error
                _words.value = listOf(
                    Word(id = 1, word = "hello", level = level, progress = 0, isLearned = false),
                    Word(id = 2, word = "world", level = level, progress = 50, isLearned = false),
                    Word(id = 3, word = "kotlin", level = level, progress = 100, isLearned = true)
                )
                _error.value = "Failed to load words: ${e.message}"
                _isLoading.value = false
            }
        }
    }

    fun getRemainingWordsCount(): Int {
        return _words.value.count { !it.isLearned }
    }

    fun getProgressPercentage(): Int {
        val totalWords = _words.value.size
        if (totalWords == 0) return 0

        val learnedWords = _words.value.count { it.isLearned }
        return (learnedWords * 100) / totalWords
    }

}