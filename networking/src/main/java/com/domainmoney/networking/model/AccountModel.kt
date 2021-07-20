package com.domainmoney.networking.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountModel(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "image_url") val imageUrl: String?,
    @Json(name = "type") val type: String,
    @Json(name = "balance") val balance: Double,
)
