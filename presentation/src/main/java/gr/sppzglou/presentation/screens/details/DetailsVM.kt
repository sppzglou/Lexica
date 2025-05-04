package gr.sppzglou.presentation.screens.details

import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.domain.ResultWrapper
import gr.sppzglou.domain.cases.GetWordUseCase
import gr.sppzglou.domain.cases.SaveWordUseCase
import gr.sppzglou.domain.inProgress
import gr.sppzglou.presentation.base.BaseVM
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailsVM @Inject constructor(
    private val getWordUseCase: GetWordUseCase,
    private val saveWordUseCase: SaveWordUseCase,
) : BaseVM() {

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<DetailsEffect>()
    val effect = _effect.asSharedFlow()

    fun handleIntent(intent: DetailsIntent) {
        when (intent) {
            is DetailsIntent.GetWord -> getWord(intent.word)
            is DetailsIntent.SaveWord -> saveWord(intent.word, intent.flag)
        }
    }

    fun handleEffect(effect: DetailsEffect) = launch {
        _effect.emit(effect)
    }

    private fun getWord(word: String) = launch {
        _uiState.update { it.copy(result = getWordUseCase(word)) }
    }

    private fun saveWord(word: String, flag: Boolean) = launch {
        saveWordUseCase(word, flag)
    }
}