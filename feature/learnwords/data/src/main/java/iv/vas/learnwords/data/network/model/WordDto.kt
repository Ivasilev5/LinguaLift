package iv.vas.learnwords.data.network.model

data class WordDto(
    val word: String,
    val phonetic: String?,
    val phonetics: List<PhoneticDto>,
    val meanings: List<MeaningDto>,
    val license: LicenseDto?,
    val sourceUrls: List<String>
)

data class PhoneticDto(
    val text: String?,
    val audio: String?,
    val sourceUrl: String? = null,
    val license: LicenseDto? = null
)

data class MeaningDto(
    val partOfSpeech: String,
    val definitions: List<DefinitionDto>,
    val synonyms: List<String>,
    val antonyms: List<String>
)

data class DefinitionDto(
    val definition: String,
    val example: String? = null,
    val synonyms: List<String>? = emptyList(),
    val antonyms: List<String>? = emptyList()
)

data class LicenseDto(
    val name: String,
    val url: String
)
