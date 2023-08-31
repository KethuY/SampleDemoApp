package com.example.sampledemoapp.repository

sealed class DemoNutResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : DemoNutResult<T>(data)

    class Error<T>(message: String?, data: T? = null) : DemoNutResult<T>(data, message)

    class Loading<T> : DemoNutResult<T>()
}