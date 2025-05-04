package gr.sppzglou.data.remote

import gr.sppzglou.data.remote.dto.WordDto
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("entries/en/{word}")
    suspend fun fetchWord(
        @Path("word") word: String
    ): List<WordDto>

}