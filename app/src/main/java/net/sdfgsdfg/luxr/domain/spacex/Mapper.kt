package net.sdfgsdfg.luxr.domain.spacex

import net.sdfgsdfg.luxr.data.spacex.model.LaunchResponse
import net.sdfgsdfg.luxr.data.spacex.model.RocketResponse
import net.sdfgsdfg.luxr.ui.dashboard.LaunchItem
import net.sdfgsdfg.luxr.ui.dashboard.RocketItem
import java.text.SimpleDateFormat
import kotlin.math.abs

// SpaceX -   LaunchesResponse -> LaunchItems   mapper
fun List<LaunchResponse>.mapToLaunchItems(
    formatting: SimpleDateFormat,
    onLaunchClick: (input: LaunchItem) -> Unit
) = this.map { response ->
    LaunchItem(
        response.id ?: abs(Math.random().hashCode()).toString(),
        response.rocket ?: "",
        response.name.orEmpty(),
        response.flight_number,
        response.details ?: "",
        response.date_utc?.let { formatting.parse(it) },
        response.success,
        response.links.patch.small,
        onLaunchClick
    )
}

// SpaceX -   RocketResponse -> RocketItem   mapper
fun RocketResponse.mapToRocketItem() = RocketItem(id, name, type, stages, active, boosters, costPerLaunch, successRatePct, country, company, description)