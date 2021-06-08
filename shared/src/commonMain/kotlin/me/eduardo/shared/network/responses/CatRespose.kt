package me.eduardo.shared.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatRespose(
   @SerialName("file")
    val file: String
)
