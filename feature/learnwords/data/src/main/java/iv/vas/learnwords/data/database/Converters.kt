package iv.vas.learnwords.data.database

import androidx.room.TypeConverter
import iv.vas.learnwords.data.database.model.CefrLevel
import iv.vas.learnwords.data.database.model.DefinitionDb
import iv.vas.learnwords.data.database.model.MeaningDb
import iv.vas.learnwords.data.database.model.PhoneticDb
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromCefrLevel(value: CefrLevel): String = value.name

    @TypeConverter
    fun toCefrLevel(value: String): CefrLevel = CefrLevel.valueOf(value)

    @TypeConverter
    fun fromPhoneticsList(value: List<PhoneticDb>): String = Json.encodeToString(value)

    @TypeConverter
    fun toPhoneticsList(value: String): List<PhoneticDb> = Json.decodeFromString(value)

    @TypeConverter
    fun fromMeaningsList(value: List<MeaningDb>): String = Json.encodeToString(value)

    @TypeConverter
    fun toMeaningsList(value: String): List<MeaningDb> = Json.decodeFromString(value)

    @TypeConverter
    fun fromDefinitionsList(value: List<DefinitionDb>): String = Json.encodeToString(value)

    @TypeConverter
    fun toDefinitionsList(value: String): List<DefinitionDb> = Json.decodeFromString(value)
}
