package gr.sppzglou.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Words")
data class WordEntity (
    @PrimaryKey
    val word: String,
    val isFav: Boolean,
    val phonetic: String?,
    val audioUrl: String?,
    val phonetics: List<PhoneticEntity> = listOf(),
    val meanings: List<MeaningEntity> = listOf(),
    val license: LicenseEntity?,
    val sourceUrls: List<String> = listOf()
)

data class PhoneticEntity(
    val text: String?,
    val audio: String?,
    val sourceUrl: String? = null,
    val license: LicenseEntity? = null
)

data class MeaningEntity(
    val partOfSpeech: String?,
    val definitions: List<DefinitionEntity>,
    val synonyms: List<String>,
    val antonyms: List<String>
)

data class DefinitionEntity(
    val definition: String?,
    val synonyms: List<String>,
    val antonyms: List<String>,
    val example: String? = null
)

data class LicenseEntity(
    val name: String?,
    val url: String?
)