package com.example.sampledemoapp.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DemoNutClientProperties {
    /**
     * Don't forget to remove Interceptors (or change Logging Level to NONE)
     * in production! Otherwise people will be able to see your request and response on Log Cat.
     */
    val client = getOkhttpClient()
    private fun getOkhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpBuilder = OkHttpClient.Builder()
        httpBuilder.connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
        return httpBuilder.build()
    }

    val gsonConverter: GsonConverterFactory = GsonConverterFactory
        .create(
            GsonBuilder()
                .setLenient()
                .disableHtmlEscaping()
                .create()
        )
}