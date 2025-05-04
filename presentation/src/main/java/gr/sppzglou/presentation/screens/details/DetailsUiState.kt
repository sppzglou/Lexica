package gr.sppzglou.presentation.screens.details

import gr.sppzglou.domain.ResultWrapper
import gr.sppzglou.domain.models.WordDomain
import gr.sppzglou.presentation.base.BaseUiState
import kotlinx.coroutines.flow.Flow

data class DetailsUiState(
    override val result: ResultWrapper<Flow<WordDomain>> = ResultWrapper.Loading
) : BaseUiState<Flow<WordDomain>>