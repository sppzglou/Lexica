package gr.sppzglou.presentation.screens.details

sealed interface DetailsEffect {
    data object BackPress : DetailsEffect
}