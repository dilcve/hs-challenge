package data.api

import data.dto.CountriesDto
import data.response.PartnerResponseWrapper
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("/candidateTest/v3/problem/dataset?userKey=581368527933628795cff186f74d")
    suspend fun getCandidates(): PartnerResponseWrapper

    @POST("https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=581368527933628795cff186f74d")
    suspend fun sendConferenceList(@Body countriesDto: CountriesDto): Response<ResponseBody>

}