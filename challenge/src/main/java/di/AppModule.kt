package di

import data.api.ApiService
import data.repository.ApiRepositoryImpl
import domain.repository.ApiRepository
import domain.usecase.GetConferencesUseCase
import domain.usecase.SendSolutionUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_API_URL = "https://candidate.hubteam.com"

val appModule = module {
    single { ApiRepositoryImpl(get()) } bind ApiRepository::class
    single { GetConferencesUseCase(get()) }
    single { SendSolutionUseCase(get()) }

    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}