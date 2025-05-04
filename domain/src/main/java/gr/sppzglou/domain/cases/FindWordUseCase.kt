package gr.sppzglou.domain.cases

import gr.sppzglou.domain.Repository

class FindWordUseCase(
    private val repo: Repository
) {

    suspend operator fun invoke(word: String) = repo.findWord(word)
}