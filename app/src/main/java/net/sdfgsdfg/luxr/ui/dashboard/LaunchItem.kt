package net.sdfgsdfg.luxr.ui.dashboard

import java.io.Serializable
import java.util.*

/**
 * UI object for populating the SpaceX launches list view.
 */

sealed class ListItem() : Serializable

class HeaderItem(val text: String) : ListItem()
class LaunchItem(
    val id: String,
    val rocketId: String,
    val missionName: String,
    val flightNumber: Int?,
    val title: String,
    val date: Date?,
    val success: Boolean?,
    val thumbnailImgUrl: String?, // For displaying the thumbnail image of spacex launch badges with Picasso
    val click: (item: LaunchItem) -> Unit
) : ListItem()
