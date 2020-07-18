package domain.usecase

import domain.model.Conference
import domain.model.Partner
import domain.repository.ApiRepository
import java.time.LocalDate


class GetConferencesUseCase(private val apiRepository: ApiRepository) {
    private suspend fun getPartnersByCountry(): Map<String, List<Partner>> {
        val partners = apiRepository.getPartners()

        for (partner in partners) {
            val dates = partner.availableDates
            val possibleDate = mutableListOf<LocalDate>()

            for (i in dates.indices) {
                if (i < dates.size - 1) {
                    if (dates[i].plusDays(1) == dates[i + 1]) {
                        possibleDate.add(dates[i])
                    }
                }
            }
            partner.availableDates = possibleDate
        }
        return partners.groupBy { it.country }
    }

    suspend fun getConferences(): List<Conference> {
        val partnersByCountry = getPartnersByCountry()

        val conferences = mutableListOf<Conference>()

        partnersByCountry.forEach { (countryKey, partners) ->
            val allDatesByCountry = partners.flatMap { it.availableDates }
                .map { it to mutableListOf<String>() }.toMap()

            allDatesByCountry.forEach { (date, emailList) ->
                partners.forEach { partner ->
                    if (partner.availableDates.contains(date)) {
                        emailList.add(partner.email)
                    }
                }
            }

            allDatesByCountry.toSortedMap().maxBy {
                it.value.size
            }?.also { best ->
                conferences.add(
                    Conference(
                        attendeeCount = best.value.size,
                        attendees = best.value,
                        country = countryKey,
                        startDate = best.key.toString()
                    )
                )
            } ?: run {
                conferences.add(Conference(country = countryKey))
            }
        }

        return conferences
    }
}