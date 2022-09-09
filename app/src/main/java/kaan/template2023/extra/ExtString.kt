package kaan.template2023.extra

import java.util.*

/**
 * Force Titlecase on the string
 */
fun String.titlecase() = lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
