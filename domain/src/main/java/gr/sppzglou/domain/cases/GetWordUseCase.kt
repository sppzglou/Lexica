package gr.sppzglou.domain.cases

import gr.sppzglou.domain.Repository

class GetWordUseCase(
    private val repo: Repository
) {

    operator fun invoke(word: String) = repo.getWord(word)
}