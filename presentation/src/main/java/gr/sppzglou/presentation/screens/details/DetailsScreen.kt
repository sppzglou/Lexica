package gr.sppzglou.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gr.sppzglou.presentation.components.DisposeFlow


@OptIn(ExperimentalMaterial3Api::class)
@Destination<RootGraph>
@Composable
fun DetailsScreen(
    nav: DestinationsNavigator,
    wordId: String
) {
    val vm = hiltViewModel<DetailsVM>()
    val state by vm.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(wordId) {
        vm.handleIntent(DetailsIntent.GetWord(wordId))
    }

    DisposeFlow(vm.effect) { effect ->
        when (effect) {
            DetailsEffect.BackPress -> nav.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Word Details") },
                navigationIcon = {
                    IconButton(onClick = {
                        vm.handleEffect(DetailsEffect.BackPress)
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (state.isSuccess) {
                        val flow by state.getDataOrNull()!!.collectAsStateWithLifecycle(null)

                        flow?.let { word ->
                            IconButton(onClick = {
                                 vm.handleIntent(DetailsIntent.SaveWord(wordId, !word.isFav))
                            }) {
                                Icon(
                                    imageVector = if (word.isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "Save"
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { padding ->
        when {
            state.isSuccess -> {
                val flow by state.getDataOrNull()!!.collectAsStateWithLifecycle(null)

                flow?.let { word ->
                    LazyColumn(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        item {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(word.word, style = MaterialTheme.typography.headlineLarge)

                                word.phonetic?.let {
                                    Text(it, style = MaterialTheme.typography.titleMedium)
                                }

                                word.audioUrl?.let { audio ->
                                    Button(
                                        onClick = { /* Play audio with MediaPlayer or Intent */ },
                                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                                    ) {
                                        Icon(Icons.Default.Add, contentDescription = null)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Listen")
                                    }
                                }
                            }
                        }

                        items(word.meanings) { meaning ->
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                meaning.partOfSpeech?.let {
                                    Text(it.replaceFirstChar { c -> c.uppercaseChar() },
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                meaning.definitions.forEach { def ->
                                    Card(
                                        modifier = Modifier.fillMaxWidth(),
                                        elevation = CardDefaults.cardElevation(),
                                        shape = RoundedCornerShape(12.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(16.dp),
                                            verticalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            def.definition?.let {
                                                Text("• $it", style = MaterialTheme.typography.bodyMedium)
                                            }
                                            def.example?.let {
                                                Text(
                                                    "Example: \"$it\"",
                                                    style = MaterialTheme.typography.bodySmall,
                                                    fontStyle = FontStyle.Italic
                                                )
                                            }
                                        }
                                    }
                                }

                                if (meaning.synonyms.isNotEmpty()) {
                                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        meaning.synonyms.forEach {
                                            AssistChip(
                                                onClick = {},
                                                label = { Text(it) }
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        if (word.sourceUrls.isNotEmpty()) {
                            item {
                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Text("Sources", style = MaterialTheme.typography.titleMedium)
                                    word.sourceUrls.forEach {
                                        Text(
                                            it,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            state.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.isFailure -> {
                val msg = state.getErrorMessage().orEmpty()
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        msg,
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}