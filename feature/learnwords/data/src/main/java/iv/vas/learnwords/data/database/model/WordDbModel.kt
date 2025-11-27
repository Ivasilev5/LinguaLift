package iv.vas.learnwords.data.database.model

import androidx.room.Entity


@Entity("words_table")
data class WordDbModel(
    val word : String,
    val phonetic : String?,
    val phonetics: List<Phonetic>,
    val meanings : List<Meaning>

)
data class Phonetic(
    val text : String?,
    val audio : String
)
data class Meaning(
    val partOfSpeech : String,
    val definitions : List<Definition>
)
data class Definition(
    val definition : String,
    val example : String?
)