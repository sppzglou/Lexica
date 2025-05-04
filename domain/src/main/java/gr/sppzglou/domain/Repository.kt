package gr.sppzglou.domain

import gr.sppzglou.domain.models.WordDomain
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun findWord(word: String): ResultWrapper<Unit>

    fun getWord(word: String): ResultWrapper<Flow<WordDomain>>

    suspend fun saveWord(word: String, flag: Boolean): Unit

    fun getFavs(): ResultWrapper<Flow<List<WordDomain>>>
}