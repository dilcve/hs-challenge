package data.response

import com.google.gson.annotations.SerializedName
import domain.model.Partner
import java.time.LocalDate

data class PartnerResponseWrapper(@SerializedName("partners") val partners: List<PartnerResponse>)
data class PartnerResponse(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("availableDates")
    val availableDates: List<String>
)

fun PartnerResponse.mapTo() =
    Partner(this.firstName, this.lastName, this.email, this.country, this.availableDates.map { LocalDate.parse(it) })

