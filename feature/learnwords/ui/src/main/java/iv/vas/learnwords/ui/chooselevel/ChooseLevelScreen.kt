package iv.vas.learnwords.ui.chooselevel

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.border
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import iv.vas.core_ui.theme.LinguaLiftTheme

data class LanguageLevel(
    val level: String,
    val wordCount: String,
    val levelIcon: Int
)

@Composable
fun ChooseLevelScreen(
    modifier: Modifier = Modifier,
    viewModel: ChooseLevelViewModel = hiltViewModel(),
    onContinueClicked: (String) -> Unit = {}
) {
    val selectedLevels by viewModel.selectedLevels.collectAsState()
    val canContinue by viewModel.canContinue.collectAsState()
    val availableLevels = viewModel.availableLevels

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
            items(availableLevels) { level ->
                LevelCard(
                    level = level,
                    isSelected = selectedLevels.contains(level),
                    onCheckedChange = { isChecked ->
                        viewModel.toggleLevelSelection(level)
                    }
                )
            }
        }

        Button(
            onClick = {
                viewModel.getSelectedLevelCode()?.let { levelCode ->
                    onContinueClicked(levelCode)
                }
            },
            enabled = canContinue,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = if (canContinue)
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

// Previews are disabled for now since they require ViewModel
// @Preview(showBackground = true)
// @Composable
// private fun ChooseLevelScreenLightPreview() {
//     LinguaLiftTheme(darkTheme = false) {
//         ChooseLevelScreen()
//     }
// }
//
// @Preview(showBackground = true)
// @Composable
// private fun ChooseLevelScreenDarkPreview() {
//     LinguaLiftTheme(darkTheme = true) {
//         ChooseLevelScreen()
//     }
// }