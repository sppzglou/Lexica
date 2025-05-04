package gr.sppzglou.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import gr.sppzglou.presentation.theme.LexicaTheme


@Composable
fun App() {
    LexicaTheme {
        Surface(Modifier.fillMaxSize()) {
            Box(Modifier.systemBarsPadding()) {
                DestinationsNavHost(NavGraphs.root)
            }
        }
    }
}