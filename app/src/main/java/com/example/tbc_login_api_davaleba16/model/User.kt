package com.example.tbc_login_api_davaleba16.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "email") val email: String = "eve.holt@reqres.in",
    @Json(name = "password") val password: String = "fsdfsdf"
)
