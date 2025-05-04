package gr.sppzglou.presentation.screens.search

import gr.sppzglou.domain.ResultWrapper
import gr.sppzglou.presentation.base.BaseUiState

data class SearchUiState(
    override val result: ResultWrapper<Unit> = ResultWrapper.Awaiting,
    val search: String = ""
) : BaseUiState<Unit>