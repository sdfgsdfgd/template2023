package kaan.template2023.data.spacex.model

data class Payload(
    val customers: List<String>,
    val manufacturer: String,
    val nationality: String,
    val norad_id: List<Any>,
    val payload_id: String,
    val reused: Boolean
)