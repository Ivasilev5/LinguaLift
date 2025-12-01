package iv.vas.learnwords.data.mapper

import iv.vas.learnwords.domain.model.Word
import iv.vas.learnwords.data.database.model.WordDbModel
import iv.vas.learnwords.data.database.model.PhoneticDb as DbPhonetic
import iv.vas.learnwords.data.database.model.MeaningDb as DbMeaning
import iv.vas.learnwords.data.database.model.DefinitionDb as DbDefinition
import iv.vas.learnwords.domain.model.Meaning as DomainMeaning
import iv.vas.learnwords.domain.model.Definition as DomainDefinition

//fun Word.toWordDbModel(): WordDbModel {
//    return WordDbModel(
//        word = word,
//        phonetic = phonetic,
//        phonetics = phonetics.map { it.toDbPhonetic() },
//        meanings = meanings.map { it.toDbMeaning() }
//    )
//}



private fun DomainMeaning.toDbMeaning(): DbMeaning {
    return DbMeaning(
        partOfSpeech = partOfSpeech,
        definitions = definitions.map { it.toDbDefinition() }
    )
}

private fun DomainDefinition.toDbDefinition(): DbDefinition {
    return DbDefinition(
        definition = definition,
        example = example
    )
}
