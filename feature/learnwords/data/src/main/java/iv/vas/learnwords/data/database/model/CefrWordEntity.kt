package iv.vas.learnwords.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("cefr_words")
data class CefrWordEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val level : CefrLevel
)

enum class CefrLevel{
    A1,A2,B1,B2,C1
}