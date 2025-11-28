package iv.vas.learnwords.domain.model

data class Word(
    val id: Int,
    val word: String,
    val level: String,
    val progress: Int = 0,
    val isLearned: Boolean = false,
    val phonetic: String? = null,
    val definition: String? = null,
    val example: String? = null
)

data class WordDetails(
    val word: String,
    val phonetic: String?,
    val phonetics: List<Phonetic>,
    val meanings: List<Meaning>
)

data class Phonetic(
    val text: String?,
    val audio: String
)

data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>
)

data class Definition(
    val definition: String,
    val example: String?
)