package gr.sppzglou.data.mappers

import gr.sppzglou.data.local.entities.DefinitionEntity
import gr.sppzglou.data.local.entities.LicenseEntity
import gr.sppzglou.data.local.entities.MeaningEntity
import gr.sppzglou.data.local.entities.PhoneticEntity
import gr.sppzglou.data.local.entities.WordEntity
import gr.sppzglou.domain.models.DefinitionDomain
import gr.sppzglou.domain.models.LicenseDomain
import gr.sppzglou.domain.models.MeaningDomain
import gr.sppzglou.domain.models.PhoneticDomain
import gr.sppzglou.domain.models.WordDomain

fun WordEntity.toDomain(): WordDomain = WordDomain(
    word = word,
    isFav = isFav,
    phonetic = phonetic,
    audioUrl = audioUrl,
    phonetics = phonetics.map { it.toDomain() },
    meanings = meanings.map { it.toDomain() },
    license = license?.toDomain(),
    sourceUrls = sourceUrls
)

fun PhoneticEntity.toDomain(): PhoneticDomain = PhoneticDomain(
    text = text,
    audio = audio,
    sourceUrl = sourceUrl,
    license = license?.toDomain()
)

fun MeaningEntity.toDomain(): MeaningDomain = MeaningDomain(
    partOfSpeech = partOfSpeech,
    definitions = definitions.map { it.toDomain() },
    synonyms = synonyms,
    antonyms = antonyms
)

fun DefinitionEntity.toDomain(): DefinitionDomain = DefinitionDomain(
    definition = definition,
    synonyms = synonyms,
    antonyms = antonyms,
    example = example
)

fun LicenseEntity.toDomain(): LicenseDomain = LicenseDomain(
    name = name,
    url = url
)