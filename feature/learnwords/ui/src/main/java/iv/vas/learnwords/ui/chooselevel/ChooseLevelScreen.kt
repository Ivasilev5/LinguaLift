package iv.vas.learnwords.ui.chooselevel

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.border
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import iv.vas.core_ui.R
import iv.vas.core_ui.theme.LinguaLiftTheme

data class LanguageLevel(
    val level: String,
    val wordCount: String,
    val levelIcon : Int
)

private val languageLevels = listOf(
    LanguageLevel("A1", "1-100 words" , iv.vas.learnwords.ui.R.drawable.level_a1),
    LanguageLevel("A2", "101 - 1k words", iv.vas.learnwords.ui.R.drawable.level_a2),
    LanguageLevel("B1", "1k - 2k words", iv.vas.learnwords.ui.R.drawable.level_b1),
    LanguageLevel("B2", "2k - 3k words", iv.vas.learnwords.ui.R.drawable.level_b2),
    LanguageLevel("C1", "3k - 4k words", iv.vas.learnwords.ui.R.drawable.level_c1 )
)

@Composable
fun ChooseLevelScreen(
    modifier: Modifier = Modifier,
    onLevelSelected: (List<LanguageLevel>) -> Unit = {},
    onContinueClicked: () -> Unit = {}
) {
    val selectedLevels = remember { mutableStateListOf<LanguageLevel>() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = "Select categories for\nlearning language",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(Modifier.height(15.dp))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(languageLevels) { level ->
                LevelCard(
                    level = level,
                    isSelected = selectedLevels.contains(level),
                    onCheckedChange = { isChecked ->
                        if (isChecked) {
                            selectedLevels.add(level)
                        } else {
                            selectedLevels.remove(level)
                        }
                    }
                )
            }
        }
        Button(
            onClick = {
                onLevelSelected(selectedLevels.toList())
                onContinueClicked()
            },
            enabled = selectedLevels.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 24.dp,),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = if (selectedLevels.isNotEmpty())
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp
            )
        ) {
            Text(
                text = "Continue",
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(Modifier.height(40.dp))
    }
}

@Composable
private fun LevelCard(
    level: LanguageLevel,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (isSelected)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    MaterialTheme.colorScheme.secondary
            )
            .then(
                if (isSelected) {
                    Modifier.border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(16.dp)
                    )
                } else {
                    Modifier
                }
            ),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Image(
            painter = painterResource(level.levelIcon),
            modifier = Modifier.padding(start = 10.dp),
            contentDescription = null
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = level.wordCount,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.weight(1f))

        Checkbox(
            modifier = Modifier.clip(RoundedCornerShape(6.dp)),
            checked = isSelected,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant,
                checkmarkColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChooseLevelScreenLightPreview() {
    LinguaLiftTheme(darkTheme = false) {
        ChooseLevelScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun ChooseLevelScreenDarkPreview() {
    LinguaLiftTheme(darkTheme = true) {
        ChooseLevelScreen()
    }
}