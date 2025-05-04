package gr.sppzglou.presentation.screens.favs


sealed class FavsIntent {
    data object GetFavs : FavsIntent()
}