package iv.vas.learnwords.data.mapper

import iv.vas.learnwords.data.database.model.CefrWordEntity
import iv.vas.learnwords.data.network.model.WordApiModel
import iv.vas.learnwords.domain.model.Word
import iv.vas.learnwords.domain.model.WordDetails

// Entity to Domain
fun CefrWordEntity.toDomain(): Word {
    return Word(
        id = id,
        word = word,
        level = level.name,
        progress = progress,
        isLearned = isLearned,
        phonetic = phonetic,
        definition = definition,
        example = example
    )
}

fun List<CefrWordEntity>.toDomain(): List<Word> {
    return map { it.toDomain() }
}

// API to Domain
fun WordApiModel.toDomain(): Word {
    return Word(
        id = 0, // Will be set by database
        word = word,
        level = "", // Will be set by context
        progress = 0,
        isLearned = false,
        phonetic = phonetic,
        definition = meanings.firstOrNull()?.definitions?.firstOrNull()?.definition,
        example = meanings.firstOrNull()?.definitions?.firstOrNull()?.example
    )
}

fun WordApiModel.toWordDetails(): WordDetails {
    return WordDetails(
        word = word,
        phonetic = phonetic,
        phonetics = phonetics.map { it.toDomain() },
        meanings = meanings.map { it.toDomain() }
    )
}

private fun iv.vas.learnwords.data.network.model.PhoneticApiModel.toDomain(): iv.vas.learnwords.domain.model.Phonetic {
    return iv.vas.learnwords.domain.model.Phonetic(
        text = text,
        audio = audio ?: ""
    )
}

private fun iv.vas.learnwords.data.network.model.MeaningApiModel.toDomain(): iv.vas.learnwords.domain.model.Meaning {
    return iv.vas.learnwords.domain.model.Meaning(
        partOfSpeech = partOfSpeech,
        definitions = definitions.map { it.toDomain() }
    )
}

private fun iv.vas.learnwords.data.network.model.DefinitionApiModel.toDomain(): iv.vas.learnwords.domain.model.Definition {
    return iv.vas.learnwords.domain.model.Definition(
        definition = definition,
        example = example
    )
}
