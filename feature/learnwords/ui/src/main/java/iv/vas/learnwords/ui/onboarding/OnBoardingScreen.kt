package iv.vas.learnwords.ui.onboarding

import android.content.res.Configuration
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import iv.vas.core_ui.theme.LinguaLiftTheme
import iv.vas.learnwords.ui.R
@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier) {

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets(top = 60.dp)
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // Фоновое изображение
                Image(
                    painter = painterResource(id = R.drawable.onboarding_back),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                    contentScale = ContentScale.Crop
                )

                // Основное изображение, чуть ниже по вертикали
                Image(
                    painter = painterResource(id = R.drawable.onboarding_img),
                    contentDescription = null,
                    modifier = Modifier
                        .size(220.dp)
                        .offset(y = 60.dp,x = (-15).dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(Modifier.height(90.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Learn a language\neasily with cards",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Learn words using cards, choosing\nlevels that are convenient for you",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.outline
                )
                Spacer(Modifier.height(40.dp))

                Button(
                    onClick = { },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp) // увеличиваем высоту кнопки
                        .padding(horizontal = 24.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 8.dp
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.light_icon),
                        contentDescription = null,
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Get Started",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(Modifier.height(20.dp)) // дополнительный отступ снизу
            }
        }
    }
}


@Preview(
    showBackground = true,
    name = "Light Theme",
    uiMode =  Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun OnBoardingScreenPreviewLight(
) {
    LinguaLiftTheme {
        OnBoardingScreen()
    }
}
@Preview(
    showBackground = true,
    name = "Light Theme",
    uiMode =  Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun OnBoardingScreenPreviewDark(
) {
    LinguaLiftTheme {
        OnBoardingScreen()
    }

}


