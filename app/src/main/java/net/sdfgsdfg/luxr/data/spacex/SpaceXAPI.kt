package net.sdfgsdfg.luxr.data.spacex

import net.sdfgsdfg.luxr.data.spacex.model.LaunchDetails
import net.sdfgsdfg.luxr.data.spacex.model.LaunchResponse
import net.sdfgsdfg.luxr.data.spacex.model.RocketResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXAPI {
    // SpaceX All Launches
    @GET(ENDPOINT_LAUNCHES)
    suspend fun getLaunches(): List<LaunchResponse>

    // SpaceX Launch Details
    @GET(ENDPOINT_LAUNCH_DETAILS)
    suspend fun getLaunchDetails(
        @Path("launchId") launchId: String,
    ): LaunchDetails

    // SpaceX Rocket Details
    @GET(ENDPOINT_ROCKET_DETAILS)
    suspend fun getRocketDetails(
        @Path("rocketId") rocketId: String,
    ): RocketResponse

    companion object {  // Endpoints for SpaceX, 1- All launches 2- One Launch 3- One Rocket
        private const val ENDPOINT_LAUNCHES: String = "v5/launches"
        private const val ENDPOINT_LAUNCH_DETAILS = "v3/launches/{launchId}"
        private const val ENDPOINT_ROCKET_DETAILS = "v4/rockets/{rocketId}"
    }
}
