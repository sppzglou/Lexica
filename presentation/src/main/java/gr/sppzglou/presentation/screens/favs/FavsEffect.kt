package gr.sppzglou.presentation.screens.favs


sealed interface FavsEffect {
    data object BackPress : FavsEffect
    data class NavigateToWordDetails(val wordId: String) : FavsEffect
}