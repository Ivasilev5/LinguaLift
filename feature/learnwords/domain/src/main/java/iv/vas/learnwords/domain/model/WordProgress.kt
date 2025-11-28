package iv.vas.learnwords.domain.model

data class WordProgress(
    val id: Int,
    val word: String,
    val level: String,
    val progress: Int = 0,
    val isLearned: Boolean = false,
    val phonetic: String? = null,
    val definition: String? = null,
    val example: String? = null
)
