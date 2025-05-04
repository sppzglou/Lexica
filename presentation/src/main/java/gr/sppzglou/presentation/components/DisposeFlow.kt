package gr.sppzglou.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun <T> DisposeFlow(
    flow: Flow<T>,
    onCollect: suspend (T) -> Unit
) {
    DisposableEffect(flow) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            flow.collect { onCollect(it) }
        }

        onDispose {
            job.cancel()
        }
    }
}