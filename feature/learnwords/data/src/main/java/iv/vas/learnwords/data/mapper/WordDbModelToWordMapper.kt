package iv.vas.learnwords.data.mapper

import iv.vas.learnwords.data.database.model.WordDbModel
import iv.vas.learnwords.domain.model.Word
import iv.vas.learnwords.domain.model.Meaning
import iv.vas.learnwords.domain.model.Definition
import iv.vas.learnwords.data.database.model.PhoneticDb as DbPhonetic
import iv.vas.learnwords.data.database.model.MeaningDb as DbMeaning
import iv.vas.learnwords.data.database.model.DefinitionDb as DbDefinition

//fun WordDbModel.toWord(): Word {
//    return Word(
//        word = word,
//        phonetic = phonetic,
//        phonetics = phonetics.map { it.toDomainPhonetic() },
//        meanings = meanings.map { it.toDomainMeaning() }
//    )
//}



private fun DbMeaning.toDomainMeaning(): Meaning {
    return Meaning(
        partOfSpeech = partOfSpeech,
        definitions = definitions.map { it.toDomainDefinition() }
    )
}

private fun DbDefinition.toDomainDefinition(): Definition {
    return Definition(
        definition = definition,
        example = example
    )
}
