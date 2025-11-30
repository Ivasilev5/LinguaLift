package iv.vas.learnwords.data.mapper

import iv.vas.learnwords.data.network.model.WordDto
import iv.vas.learnwords.domain.model.Word
import iv.vas.learnwords.domain.model.Phonetic
import iv.vas.learnwords.domain.model.Meaning
import iv.vas.learnwords.domain.model.Definition

fun WordDto.toWord(): Word {
    return Word(
        word = word,
        phonetic = phonetic,
        phonetics = phonetics.map { it.toPhonetic() },
        meanings = meanings.map { it.toMeaning() }
    )
}

private fun iv.vas.learnwords.data.network.model.PhoneticDto.toPhonetic(): Phonetic {
    return Phonetic(
        text = text,
        audio = audio ?: ""
    )
}

private fun iv.vas.learnwords.data.network.model.MeaningDto.toMeaning(): Meaning {
    return Meaning(
        partOfSpeech = partOfSpeech,
        definitions = definitions.map { it.toDefinition() }
    )
}

private fun iv.vas.learnwords.data.network.model.DefinitionDto.toDefinition(): Definition {
    return Definition(
        definition = definition,
        example = example
    )
}
