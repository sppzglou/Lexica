package gr.sppzglou.data.remote.dto

data class WordDto(
    val word: String,
    val phonetic: String?,
    val audioUrl: String?,
    val phonetics: List<PhoneticDto> = listOf(),
    val meanings: List<MeaningDto> = listOf(),
    val license: LicenseDto?,
    val sourceUrls: List<String> = listOf()
)

data class PhoneticDto(
    val text: String?,
    val audio: String?,
    val sourceUrl: String? = null,
    val license: LicenseDto? = null
)

data class MeaningDto(
    val partOfSpeech: String?,
    val definitions: List<DefinitionDto> = listOf(),
    val synonyms: List<String> = listOf(),
    val antonyms: List<String> = listOf()
)

data class DefinitionDto(
    val definition: String?,
    val synonyms: List<String> = listOf(),
    val antonyms: List<String> = listOf(),
    val example: String? = null
)

data class LicenseDto(
    val name: String?,
    val url: String?
)