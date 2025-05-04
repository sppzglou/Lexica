package gr.sppzglou.data.remote

import gr.sppzglou.data.remote.dto.WordDto
import gr.sppzglou.domain.ResultWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val api: ApiService
) {
    suspend fun fetchWord(word: String): ResultWrapper<List<WordDto>> = handleApiRes {
        api.fetchWord(word)
    }
}