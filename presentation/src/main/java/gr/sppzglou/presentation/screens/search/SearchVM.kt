package gr.sppzglou.presentation.screens.search

import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.domain.ResultWrapper
import gr.sppzglou.domain.cases.FindWordUseCase
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
class SearchVM @Inject constructor(
    private val findWordUseCase: FindWordUseCase,
) : BaseVM() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<SearchEffect>()
    val effect = _effect.asSharedFlow()

    fun handleIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.Typing -> searchSave(intent.search)
            is SearchIntent.SearchClick -> fetchWord()
        }
    }

    fun handleEffect(effect: SearchEffect) = launch {
        _effect.emit(effect)
    }

    private fun searchSave(txt: String) {
        _uiState.update { it.copy(search = txt) }
    }

    private fun fetchWord() = launch {
        val search = _uiState.value.search

        _uiState.update {
            it.copy(result = inProgress())
        }

        val result = findWordUseCase(search)
        when (result) {
            is ResultWrapper.Success, is ResultWrapper.Failure -> {
                _uiState.update { it.copy(result = result) }
            }

            else -> Unit
        }
        if (result is ResultWrapper.Success) {
            handleEffect(SearchEffect.NavigateToWordDetails(search))
        }
    }
}