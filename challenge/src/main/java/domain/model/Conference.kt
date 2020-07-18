package domain.model

data class Conference(
    val attendeeCount: Int = 0,
    val attendees: List<String> = emptyList(),
    val country: String,
    val startDate: String? = null
)