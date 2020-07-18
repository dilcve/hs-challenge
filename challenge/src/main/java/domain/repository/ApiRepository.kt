package domain.repository

import domain.model.Conference
import domain.model.Partner

interface ApiRepository {
    suspend fun getPartners(): List<Partner>

    suspend fun sendSolution(conferences: List<Conference>): Boolean
}