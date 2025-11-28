package iv.vas.learnwords.ui.chooselevel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import iv.vas.learnwords.data.repository.WordDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChooseLevelViewModel @Inject constructor() : ViewModel() {

    private val _selectedLevels = MutableStateFlow<List<LanguageLevel>>(emptyList())
    val selectedLevels: StateFlow<List<LanguageLevel>> = _selectedLevels.asStateFlow()

    private val _canContinue = MutableStateFlow(false)
    val canContinue: StateFlow<Boolean> = _canContinue.asStateFlow()

    val availableLevels = listOf(
        LanguageLevel("A1", "${WordDataProvider.a1Words.size} words", iv.vas.learnwords.ui.R.drawable.level_a1),
        LanguageLevel("A2", "${WordDataProvider.a2Words.size} words", iv.vas.learnwords.ui.R.drawable.level_a2),
        LanguageLevel("B1", "${WordDataProvider.b1Words.size} words", iv.vas.learnwords.ui.R.drawable.level_b1),
        LanguageLevel("B2", "${WordDataProvider.b2Words.size} words", iv.vas.learnwords.ui.R.drawable.level_b2),
        LanguageLevel("C1", "${WordDataProvider.c1Words.size} words", iv.vas.learnwords.ui.R.drawable.level_c1)
    )

    fun toggleLevelSelection(level: LanguageLevel) {
        val currentSelection = _selectedLevels.value.toMutableList()
        if (currentSelection.contains(level)) {
            currentSelection.remove(level)
        } else {
            // Allow only single selection for now - user can only learn one level at a time
            currentSelection.clear()
            currentSelection.add(level)
        }
        _selectedLevels.value = currentSelection
        _canContinue.value = currentSelection.isNotEmpty()
    }

    fun clearSelection() {
        _selectedLevels.value = emptyList()
        _canContinue.value = false
    }

    fun getSelectedLevel(): LanguageLevel? {
        return _selectedLevels.value.firstOrNull()
    }

    fun getSelectedLevelCode(): String? {
        return getSelectedLevel()?.level
    }
}
