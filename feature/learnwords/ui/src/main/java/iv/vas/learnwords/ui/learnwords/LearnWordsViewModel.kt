package iv.vas.learnwords.ui.learnwords

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iv.vas.core_media.audio.AudioPlayer
import iv.vas.learnwords.domain.model.Word
import iv.vas.learnwords.domain.usecase.learnwords.GetWordDetailsUseCase
import iv.vas.learnwords.domain.usecase.learnwords.LoadNextWordUseCase
import iv.vas.learnwords.domain.usecase.learnwords.MarkWordLearnedUseCase
import iv.vas.learnwords.domain.usecase.learnwords.UpdateWordProgressUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearnWordsViewModel @Inject constructor(
    private val loadNextWordUseCase: LoadNextWordUseCase,
    private val getWordDetailsUseCase: GetWordDetailsUseCase,
    private val updateWordProgressUseCase: UpdateWordProgressUseCase,
    private val markWordLearnedUseCase: MarkWordLearnedUseCase
) : ViewModel() {

    private val _currentWord = MutableStateFlow<Word?>(null)
    val currentWord: StateFlow<Word?> = _currentWord.asStateFlow()

    private val _definition = MutableStateFlow<String?>(null)
    val definition: StateFlow<String?> = _definition.asStateFlow()

    private val _wordAudio = MutableStateFlow<String?>(null)
    val wordAudio: StateFlow<String?> = _wordAudio.asStateFlow()

    private val _example = MutableStateFlow<String?>(null)
    val example: StateFlow<String?> = _example.asStateFlow()

    private val _phonetic = MutableStateFlow<String?>(null)
    val phonetic: StateFlow<String?> = _phonetic.asStateFlow()

    private val _partOfSpeech = MutableStateFlow<String?>(null)
    val partOfSpeech: StateFlow<String?> = _partOfSpeech.asStateFlow()

    private val _progress = MutableStateFlow(0)
    val progress: StateFlow<Int> = _progress.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    val audioPlayer = AudioPlayer()

    fun loadNextWord(level: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val word = loadNextWordUseCase(level)
                _currentWord.value = word

                if (word != null) {
                    loadWordDetails(word.word)
                }
            } catch (e: Exception) {
                _error.value = "Failed to load next word: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadWordByName(wordName: String, level: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                // Create a word object for display
                _currentWord.value = iv.vas.learnwords.domain.model.Word(
                    id = 0, // Will be set if found in database
                    word = wordName,
                    level = level,
                    progress = 0,
                    isLearned = false
                )

                // Load details from API
                loadWordDetails(wordName)
                Log.d("loadWordByName", "")
                // Try to find the word in database to get progress
                try {
                    val dbWord = loadNextWordUseCase(level)
                    if (dbWord?.word?.equals(wordName, ignoreCase = true) == true) {
                        _currentWord.value = dbWord
                        _progress.value = dbWord.progress
                    }
                } catch (e: Exception) {
                    // Word not found in database, continue with API data
                }

            } catch (e: Exception) {
                _error.value = "Failed to load word: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadWordDetails(word: String) {
        viewModelScope.launch {
            try {
                val details = getWordDetailsUseCase(word)

                // Extract first meaning and definition
                val firstMeaning = details.meanings.firstOrNull()
                val firstDefinition = firstMeaning?.definitions?.firstOrNull()

                _definition.value = firstDefinition?.definition
                _example.value = firstDefinition?.example
                _partOfSpeech.value = firstMeaning?.partOfSpeech?.replaceFirstChar { it.uppercase() }
                _wordAudio.value = details.audioUrl

                // Extract phonetic
                _phonetic.value = details.phonetic ?: ""

                // Set progress from current word if available
                _currentWord.value?.let { current ->
                    _progress.value = current.progress
                }
            } catch (e: Exception) {
                _error.value = "Failed to load word details: ${e.message}"
            }
        }
    }

    fun updateProgress(percent: Int) {
        viewModelScope.launch {
            try {
                _currentWord.value?.let { word ->
                    updateWordProgressUseCase(word.id, percent)
                    _progress.value = percent

                    // Update the word in state
                    _currentWord.value = word.copy(progress = percent)
                }
            } catch (e: Exception) {
                _error.value = "Failed to update progress: ${e.message}"
            }
        }
    }
    fun playWordAudio(url : String){
        audioPlayer.play(url)
    }

    fun markLearned() {
        viewModelScope.launch {
            try {
                _currentWord.value?.let { word ->
                    markWordLearnedUseCase(word.id)
                    _progress.value = 100

                    // Update the word in state
                    _currentWord.value = word.copy(isLearned = true, progress = 100)
                }
            } catch (e: Exception) {
                _error.value = "Failed to mark word as learned: ${e.message}"
            }
        }
    }

    fun clearError() {
        _error.value = null
    }

    override fun onCleared() {
        audioPlayer.stop()
    }
}
