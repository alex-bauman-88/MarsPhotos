package com.example.marsphotos.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
When kotlinx serialization parses the JSON, it matches the keys by name
and fills the data objects with appropriate values.
*/

/*To use variable names in your data class that differ from the key names in the JSON response,
use the @SerialName annotation.*/

@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName(value = "img_src") val imgSrc: String
)
