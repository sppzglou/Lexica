package gr.sppzglou.presentation.screens.details

sealed class DetailsIntent {
    data class GetWord(val word: String) : DetailsIntent()
    data class SaveWord(val word: String, val flag: Boolean) : DetailsIntent()
}