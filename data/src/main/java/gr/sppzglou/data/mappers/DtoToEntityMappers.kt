package gr.sppzglou.data.mappers

import gr.sppzglou.data.local.entities.DefinitionEntity
import gr.sppzglou.data.local.entities.LicenseEntity
import gr.sppzglou.data.local.entities.MeaningEntity
import gr.sppzglou.data.local.entities.PhoneticEntity
import gr.sppzglou.data.local.entities.WordEntity
import gr.sppzglou.data.remote.dto.DefinitionDto
import gr.sppzglou.data.remote.dto.LicenseDto
import gr.sppzglou.data.remote.dto.MeaningDto
import gr.sppzglou.data.remote.dto.PhoneticDto
import gr.sppzglou.data.remote.dto.WordDto


fun WordDto.toEntity(): WordEntity = WordEntity(
    word = word,
    phonetic = phonetic,
    isFav = false,
    audioUrl = audioUrl,
    phonetics = phonetics.map { it.toEntity() },
    meanings = meanings.map { it.toEntity() },
    license = license?.toEntity(),
    sourceUrls = sourceUrls
)

fun PhoneticDto.toEntity(): PhoneticEntity = PhoneticEntity(
    text = text,
    audio = audio,
    sourceUrl = sourceUrl,
    license = license?.toEntity()
)

fun MeaningDto.toEntity(): MeaningEntity = MeaningEntity(
    partOfSpeech = partOfSpeech,
    definitions = definitions.map { it.toEntity() },
    synonyms = synonyms,
    antonyms = antonyms
)

fun DefinitionDto.toEntity(): DefinitionEntity = DefinitionEntity(
    definition = definition,
    synonyms = synonyms,
    antonyms = antonyms,
    example = example
)

fun LicenseDto.toEntity(): LicenseEntity = LicenseEntity(
    name = name,
    url = url
)