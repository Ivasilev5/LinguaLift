package iv.vas.learnwords.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("words_table")
data class WordDbModel(
    @PrimaryKey
    val word : String,
    val phonetic : String?,
    val phonetics: List<PhoneticDb>,
    val meanings : List<MeaningDb>

)
data class PhoneticDb(
    val text : String?,
    val audio : String
)
data class MeaningDb(
    val partOfSpeech : String,
    val definitions : List<DefinitionDb>
)
data class DefinitionDb(
    val definition : String,
    val example : String?
)