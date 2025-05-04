package gr.sppzglou.presentation.screens.favs

import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.domain.ResultWrapper
import gr.sppzglou.domain.cases.FindWordUseCase
import gr.sppzglou.domain.cases.GetFavsUseCase
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
class FavsVM @Inject constructor(
    private val getFavsUseCase: GetFavsUseCase,
) : BaseVM() {

    private val _uiState = MutableStateFlow(FavsUiState())
    val uiState: StateFlow<FavsUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<FavsEffect>()
    val effect = _effect.asSharedFlow()

    fun handleIntent(intent: FavsIntent) {
        when (intent) {
            FavsIntent.GetFavs -> getFavs()
        }
    }

    fun handleEffect(effect: FavsEffect) = launch {
        _effect.emit(effect)
    }

    private fun getFavs() = launch {
        _uiState.update { it.copy(result = getFavsUseCase()) }
    }
}