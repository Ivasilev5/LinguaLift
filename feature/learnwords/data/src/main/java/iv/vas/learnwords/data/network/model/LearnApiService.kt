package iv.vas.learnwords.data.network.model

import androidx.room.Query
import iv.vas.core.utils.Constants
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface LearnApiService {

    @GET("{word}")
    suspend fun getWord(@Path("word") word :String) : WordDto
}