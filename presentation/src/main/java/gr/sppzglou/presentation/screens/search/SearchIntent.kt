package gr.sppzglou.presentation.screens.search

sealed class SearchIntent {
    data class Typing(val search: String) : SearchIntent()
    data object SearchClick : SearchIntent()
}