package com.example.sampledemoapp.repository.data

import androidx.annotation.Keep

@Keep
data class DemoNutResponse(
    val data: List<DemoNut>? = null,
    val message: String="",
    val status: String=""
)

@Keep
data class DemoNut(
    val city: String,
    val country: String,
    val id: String,
    val imgURL: String,
    val name: String
)
