package gr.sppzglou.domain.models

data class WordDomain(
    val word: String,
    val isFav: Boolean,
    val phonetic: String?,
    val audioUrl: String?,
    val phonetics: List<PhoneticDomain>,
    val meanings: List<MeaningDomain>,
    val license: LicenseDomain?,
    val sourceUrls: List<String>
)

data class PhoneticDomain(
    val text: String?,
    val audio: String?,
    val sourceUrl: String?,
    val license: LicenseDomain?
)

data class MeaningDomain(
    val partOfSpeech: String?,
    val definitions: List<DefinitionDomain>,
    val synonyms: List<String>,
    val antonyms: List<String>
)

data class DefinitionDomain(
    val definition: String?,
    val synonyms: List<String>,
    val antonyms: List<String>,
    val example: String?
)

data class LicenseDomain(
    val name: String?,
    val url: String?
)