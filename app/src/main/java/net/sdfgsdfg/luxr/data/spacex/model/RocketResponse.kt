package net.sdfgsdfg.luxr.data.spacex.model

data class RocketResponse(
    val id: String?,
    val name: String?,
    val type: String?,
    val stages: Int?,
    val active: Boolean?,
    val boosters: Int?,
    val costPerLaunch: Int?,
    val successRatePct: Int?,
    val firstStage: FirstStage?,
    val secondStage: SecondStage?,
    val flickrImages: List<String?>?,
    val firstFlight: String?,
    val country: String?,
    val company: String?,
    val wikipedia: String?,
    val description: String?
)