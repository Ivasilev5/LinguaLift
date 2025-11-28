package iv.vas.learnwords.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cefr_words")
data class CefrWordEntity(
    @PrimaryKey
    val id: Int,
    val word: String,
    val level: CefrLevel,
    val progress: Int = 0, // 0-100, percentage of learning progress
    val isLearned: Boolean = false,
    val phonetic: String? = null,
    val definition: String? = null,
    val example: String? = null,
    val audioUrl: String? = null
)

enum class CefrLevel {
    A1, A2, B1, B2, C1
}
