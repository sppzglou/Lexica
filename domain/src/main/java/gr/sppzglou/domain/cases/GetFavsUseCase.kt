package gr.sppzglou.domain.cases

import gr.sppzglou.domain.Repository

class GetFavsUseCase(
    private val repo: Repository
) {

    operator fun invoke() = repo.getFavs()
}