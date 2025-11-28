package iv.vas.learnwords.data.network.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LearnApiService {

    @GET("{word}")
    suspend fun getWord(@Path("word") word: String): Response<List<WordApiModel>>
}