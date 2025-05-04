package gr.sppzglou.domain.cases

import gr.sppzglou.domain.Repository

class SaveWordUseCase(
    private val repo: Repository
) {

    suspend operator fun invoke(word: String, flag: Boolean) = repo.saveWord(word, flag)
}