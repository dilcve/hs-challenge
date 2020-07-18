package domain.usecase

import domain.model.Conference
import domain.repository.ApiRepository

class SendSolutionUseCase(private val apiRepository: ApiRepository) {

    suspend fun sendSolution(conferences: List<Conference>): Boolean {
        return apiRepository.sendSolution(conferences)
    }
}