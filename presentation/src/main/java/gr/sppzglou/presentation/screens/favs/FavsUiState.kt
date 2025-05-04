package gr.sppzglou.presentation.screens.favs

import gr.sppzglou.domain.ResultWrapper
import gr.sppzglou.domain.models.WordDomain
import gr.sppzglou.presentation.base.BaseUiState
import kotlinx.coroutines.flow.Flow

data class FavsUiState(
    override val result: ResultWrapper<Flow<List<WordDomain>>> = ResultWrapper.Loading,
) : BaseUiState<Flow<List<WordDomain>>>