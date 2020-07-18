package data.repository

import data.api.ApiService
import data.dto.CountriesDto
import data.dto.mapToDto
import data.response.mapTo
import domain.model.Conference
import domain.model.Partner
import domain.repository.ApiRepository


class ApiRepositoryImpl(private val apiService: ApiService) : ApiRepository {
    override suspend fun getPartners(): List<Partner> {

        return apiService.getCandidates().partners.map { it.mapTo() }

// I was using this code to test locally with the sample date provided, I leaved it commented because I thought it worth :)
//        val inputStream = javaClass.getResourceAsStream("/sampleData.txt") ?: throw IOException()
//
//        return Gson().fromJson(
//            inputStream.bufferedReader()
//                .use(BufferedReader::readText),
//            PartnerResponseWrapper::class.java
//        ).partners.map { it.mapTo() }


    }

    override suspend fun sendSolution(conferences: List<Conference>): Boolean {
        val response =
            apiService.sendConferenceList(CountriesDto(conferences.map { it.mapToDto() }))
        return response.isSuccessful
    }
}