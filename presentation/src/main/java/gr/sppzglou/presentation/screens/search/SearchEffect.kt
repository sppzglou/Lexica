package gr.sppzglou.presentation.screens.search

sealed interface SearchEffect {
    data class NavigateToWordDetails(val wordId: String) : SearchEffect
}