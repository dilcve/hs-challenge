package data.dto

import com.google.gson.annotations.SerializedName
import domain.model.Conference

data class CountriesDto(@SerializedName("countries") val countries: List<CountryDto>)

data class CountryDto(
    @SerializedName("attendeeCount")
    val attendeeCount: Int = 0,
    @SerializedName("attendees")
    val attendees: List<String> = emptyList(),
    @SerializedName("name")
    val country: String,
    @SerializedName("startDate")
    val startDate: String? = null
)

fun Conference.mapToDto() =
    CountryDto(this.attendeeCount, this.attendees, this.country, this.startDate)