import di.appModule
import domain.usecase.GetConferencesUseCase
import domain.usecase.SendSolutionUseCase
import kotlinx.coroutines.runBlocking
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class HubSpotSampleApplication : KoinComponent {

    private val getPartnersUseCase by inject<GetConferencesUseCase>()
    private val sendSolutionUseCase by inject<SendSolutionUseCase>()

    suspend fun getConferencesAndSendSolution() =
        sendSolutionUseCase.sendSolution(getPartnersUseCase.getConferences())

}

fun main() {
    startKoin {
        modules(appModule)
    }

    val isSuccess = runBlocking {
        HubSpotSampleApplication().getConferencesAndSendSolution()
    }

    println("is Success = $isSuccess")

}