package domain.model

import java.time.LocalDate

data class Partner(
    val firstName: String,
    val lastName: String,
    val email: String,
    val country: String,
    var availableDates: List<LocalDate>
)

