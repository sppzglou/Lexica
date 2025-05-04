package gr.sppzglou.data

import gr.sppzglou.data.local.LocalDataSource
import gr.sppzglou.data.mappers.toDomain
import gr.sppzglou.data.mappers.toEntity
import gr.sppzglou.data.remote.RemoteDataSource
import gr.sppzglou.domain.Repository
import gr.sppzglou.domain.ResultWrapper
import gr.sppzglou.domain.ResultWrapper.Companion.dataConverter
import gr.sppzglou.domain.errorMessage
import gr.sppzglou.domain.models.WordDomain
import gr.sppzglou.domain.success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) : Repository {

    override suspend fun findWord(word: String): ResultWrapper<Unit> {
        local.findWord(word)?.let {
            return success(Unit)
        }

        val res = remote.fetchWord(word)

        if (res is ResultWrapper.Success) {
            local.insertWords(res.data.map { it.toEntity() })
        }

        return res.dataConverter {}
    }

    override fun getWord(word: String): ResultWrapper<Flow<WordDomain>> {
        return try {
            success(local.getWord(word).map { it.toDomain() })
        } catch (e: Exception) {
            errorMessage("Something went wrong!")
        }
    }

    override suspend fun saveWord(word: String, flag: Boolean) {
        local.updateFav(word, flag)
    }

    override fun getFavs(): ResultWrapper<Flow<List<WordDomain>>> {
        return try {
            success(local.getFavs().map { it.map { it.toDomain() } })
        } catch (e: Exception) {
            errorMessage("Something went wrong!")
        }
    }

}