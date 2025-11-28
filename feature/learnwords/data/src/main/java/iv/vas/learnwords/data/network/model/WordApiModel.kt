package iv.vas.learnwords.data.network.model

data class WordApiModel(
    val word: String,
    val phonetic: String?,
    val phonetics: List<PhoneticApiModel>,
    val meanings: List<MeaningApiModel>,
    val license: LicenseApiModel?,
    val sourceUrls: List<String>
)

data class PhoneticApiModel(
    val text: String?,
    val audio: String?,
    val sourceUrl: String? = null,
    val license: LicenseApiModel? = null
)

data class MeaningApiModel(
    val partOfSpeech: String,
    val definitions: List<DefinitionApiModel>,
    val synonyms: List<String>,
    val antonyms: List<String>
)

data class DefinitionApiModel(
    val definition: String,
    val example: String? = null,
    val synonyms: List<String>? = emptyList(),
    val antonyms: List<String>? = emptyList()
)

data class LicenseApiModel(
    val name: String,
    val url: String
)
