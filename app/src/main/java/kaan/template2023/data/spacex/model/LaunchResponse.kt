package kaan.template2023.data.spacex.model

/**
 * The default LaunchResponse class for the 'all launches' endpoint
 */
data class LaunchResponse(
    val rocket: String?,
    val auto_update: Boolean?,
    val capsules: List<String>?,
    val cores: List<Core>?,
    val crew: List<Any>?,
    val date_local: String?,
    val date_precision: String?,
    val date_unix: Int?,
    val date_utc: String?,
    val details: String?,
    val failures: List<Any>?,
    val fairings: Any?,
    val flight_number: Int?,
    val links: Links,
    val id: String?,
    val launchpad: String?,
    val name: String?,
    val net: Boolean?,
    val payloads: List<String>?,
    val ships: List<Any>?,
    val static_fire_date_unix: Int?,
    val static_fire_date_utc: String?,
    val success: Boolean?,
    val tdb: Boolean?,
    val upcoming: Boolean?,
    val window: Int?
)

/**
 * 'one launch' response class for the spacex one launch endpoint
 */
data class LaunchDetails(
    val details: String?
)